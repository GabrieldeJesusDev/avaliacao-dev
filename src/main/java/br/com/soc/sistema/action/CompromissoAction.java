package br.com.soc.sistema.action;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class CompromissoAction extends Action {

	private List<CompromissoVo> compromissos = new ArrayList<>();
	private CompromissoBusiness business = new CompromissoBusiness();
	private CompromissoVo compromissoVo = new CompromissoVo();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private List<AgendaVo> agendas = new ArrayList<>();
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	private AgendaBusiness agendaBusiness = new AgendaBusiness();
	private String data;
	private String hora;

	public String todos() {
		compromissos.addAll(business.trazerTodosOsCompromissos());

		return SUCCESS;
	}

	public String novo() {
		if (compromissoVo.getCdFuncionario() == null && compromissoVo.getCdAgenda() == null) {

			carregarCombos();

			return INPUT;
		}

		if (data == null || data.isEmpty() || hora == null || hora.isEmpty()) {
			carregarCombos();
			return INPUT;
		}

		compromissoVo.setData(LocalDate.parse(data));
		compromissoVo.setHora(LocalTime.parse(hora));
		try {
			if (compromissoVo.getRowid() != null && !compromissoVo.getRowid().isEmpty()) {
				business.atualizarCompromisso(compromissoVo);
			} else {
				business.salvarCompromisso(compromissoVo);
			}

		} catch (BusinessException e) {
			addActionError(e.getMessage());
			carregarCombos();

			return INPUT;
		}
		return REDIRECT;
	}

	public String editar() {
		if (compromissoVo.getRowid() == null)
			return REDIRECT;

		compromissoVo = business.buscarCompromissoPor(compromissoVo.getRowid());

		if(compromissoVo == null) {
			return REDIRECT;
		}
		
		data = compromissoVo.getData().toString();
		hora = compromissoVo.getHora().toString();

		carregarCombos();

		return INPUT;
	}

	public String excluir() {
		if (compromissoVo.getRowid() == null || compromissoVo.getRowid().isEmpty())
			return REDIRECT;

		business.excluirCompromisso(compromissoVo.getRowid());

		return REDIRECT;
	}

	private void carregarCombos() {
		funcionarios.addAll(funcionarioBusiness.trazerTodosOsFuncionarios());
		agendas.addAll(agendaBusiness.trazerTodasAsAgendas());
	}

	public List<CompromissoVo> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<CompromissoVo> compromissos) {
		this.compromissos = compromissos;
	}

	public CompromissoVo getCompromissoVo() {
		return compromissoVo;
	}

	public void setCompromissoVo(CompromissoVo compromissoVo) {
		this.compromissoVo = compromissoVo;
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<AgendaVo> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<AgendaVo> agendas) {
		this.agendas = agendas;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}
