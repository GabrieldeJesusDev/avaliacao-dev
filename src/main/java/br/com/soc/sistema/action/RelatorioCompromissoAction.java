package br.com.soc.sistema.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.soc.sistema.business.RelatorioCompromissoBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoAction extends Action {

	private RelatorioCompromissoBusiness business = new RelatorioCompromissoBusiness();
	private List<RelatorioCompromissoVo> relatorios = new ArrayList<>();

	private String dataInicial;
	private String dataFinal;

	private InputStream arquivoExcel;

	public String abrir() {
		return INPUT;
	}

	public String gerar() {

		if (dataInicial == null || dataInicial.isEmpty() || dataFinal == null || dataFinal.isEmpty()) {

			addActionError("Data inicial e final devem ser informadas");
			return INPUT;
		}
		try {

			LocalDate inicio = LocalDate.parse(dataInicial);
			LocalDate fim = LocalDate.parse(dataFinal);

			relatorios = business.gerarRelatorio(inicio, fim);

		}catch (DateTimeParseException e) {
			addActionError("Data inicial ou final invalida");
			return INPUT;
			
		} catch (BusinessException e) {

			addActionError(e.getMessage());
			return INPUT;
		}

		return SUCCESS;
	}

	public String excel() {

		if (dataInicial == null || dataInicial.isEmpty() || dataFinal == null || dataFinal.isEmpty()) {

			addActionError("Data inicial e final precisam ser informadas");
			return INPUT;
		}

		try {

			LocalDate inicio = LocalDate.parse(dataInicial);
			LocalDate fim = LocalDate.parse(dataFinal);

			relatorios = business.gerarRelatorio(inicio, fim);

			XSSFWorkbook workbook = new XSSFWorkbook();

			Sheet sheet = workbook.createSheet("Compromissos");
			
			Row cabecalho = sheet.createRow(0);
			
			cabecalho.createCell(0).setCellValue("Codigo Funcionario");
			cabecalho.createCell(1).setCellValue("Nome Funcionario");
			cabecalho.createCell(2).setCellValue("Codigo Agenda");
			cabecalho.createCell(3).setCellValue("Nome Agenda");
			cabecalho.createCell(4).setCellValue("Data");
			cabecalho.createCell(5).setCellValue("Hora");
			
			int linha = 1;
			
			for(RelatorioCompromissoVo relatorio : relatorios) {
				Row row = sheet.createRow(linha++);
				
				row.createCell(0).setCellValue(relatorio.getCodigoFuncionario());
				row.createCell(1).setCellValue(relatorio.getNomeFuncionario());
				row.createCell(2).setCellValue(relatorio.getCodigoAgenda());
				row.createCell(3).setCellValue(relatorio.getNomeAgenda());
				row.createCell(4).setCellValue(relatorio.getData().toString());
				row.createCell(5).setCellValue(relatorio.getHora().toString());
			}
			
			ByteArrayOutputStream saida = new ByteArrayOutputStream();
			
			workbook.write(saida);
			
			arquivoExcel = new ByteArrayInputStream(saida.toByteArray());
			
			workbook.close();

			
		}catch(DateTimeParseException e) {
			addActionError("Data inicial ou final invalida");
			return INPUT;
			
		} catch (BusinessException e) {

			addActionError(e.getMessage());
			return INPUT;

		} catch (Exception e) {

			addActionError("Erro ao gerar Excel");
			return INPUT;
		}

		return "excel";
	}

	public InputStream getArquivoExcel() {
		return arquivoExcel;
	}

	public RelatorioCompromissoBusiness getBusiness() {
		return business;
	}

	public void setBusiness(RelatorioCompromissoBusiness business) {
		this.business = business;
	}

	public List<RelatorioCompromissoVo> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<RelatorioCompromissoVo> relatorios) {
		this.relatorios = relatorios;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

}
