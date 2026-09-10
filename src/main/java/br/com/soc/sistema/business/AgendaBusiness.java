package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.AgendaFilter;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {

	private AgendaDao dao;

	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}

	public List<AgendaVo> trazerTodasAsAgendas() {
		return dao.findAllAgendas();
	}

	public void salvarAgenda(AgendaVo agendaVo) {
		try {
			if (agendaVo.getNome() == null || agendaVo.getNome().trim().isEmpty())
				throw new IllegalArgumentException("Agenda nao pode estar em branco");

			if (agendaVo.getPeriodo() == null)
				throw new IllegalArgumentException("Agenda deve conter um periodo");

			if (agendaVo.getPeriodo() < 1 || agendaVo.getPeriodo() > 3)
				throw new IllegalArgumentException("Periodo invalido");

			dao.insertAgenda(agendaVo);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao de um registro");
		}
	}

	public void atualizarAgenda(AgendaVo agendaVo) {
		try {
			if (agendaVo.getRowid() == null || agendaVo.getRowid().isEmpty())
				throw new IllegalArgumentException("Codigo da agenda nao informado");

			if (agendaVo.getNome() == null || agendaVo.getNome().trim().isEmpty())
				throw new IllegalArgumentException("Nao pode estar em branco");

			if (agendaVo.getPeriodo() == null || agendaVo.getPeriodo() < 1 || agendaVo.getPeriodo() > 3)
				throw new IllegalArgumentException("Agenda deve conter um periodo");

			dao.updateAgenda(agendaVo);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a atualizacao da agenda");
		}
	}

	public AgendaVo buscarAgendaPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException("Foi informado caracter no lugar de um numero");
		}
	}

	public List<AgendaVo> filtrarAgendas(AgendaFilter filter) {
		List<AgendaVo> agendas = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			try {
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				AgendaVo agenda = dao.findByCodigo(codigo);

				if (agenda != null) {
					agendas.add(agenda);
				}
			} catch (NumberFormatException e) {
				throw new BusinessException("Foi informado caracter no lugar de um numero");
			}
			break;

		case NOME:
			agendas = dao.findAllByNome(filter.getValorBusca());
			break;
		}

		return agendas;
	}

	public void excluirAgenda(String rowid) {
		try {
			if (rowid == null || rowid.isEmpty())
				throw new IllegalArgumentException("Codigo da Agenda nao informado");
			dao.deleteAgenda(rowid);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusao da agenda");
		}
	}

}
