package br.com.soc.sistema.action;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.RelatorioCompromissoBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoAction extends Action {

	private RelatorioCompromissoBusiness business = new RelatorioCompromissoBusiness();
	private List<RelatorioCompromissoVo> relatorios = new ArrayList<>();

	private String dataInicial;
	private String dataFinal;

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

		} catch (BusinessException e) {

			addActionError(e.getMessage());
			return INPUT;
		}

		return SUCCESS;
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
