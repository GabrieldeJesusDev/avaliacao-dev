package br.com.soc.sistema.business;

import java.time.LocalTime;
import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoBusiness {

	private CompromissoDao dao;

	public CompromissoBusiness() {
		this.dao = new CompromissoDao();
	}

	public List<CompromissoVo> trazerTodosOsCompromissos() {
		return dao.findAllCompromissos();
	}

	public void salvarCompromisso(CompromissoVo compromissoVo) {
		try {
			if (compromissoVo.getCdFuncionario() == null || compromissoVo.getCdFuncionario() <= 0)
				throw new IllegalArgumentException("Codigo do funcionario nao informado");

			if (compromissoVo.getCdAgenda() == null || compromissoVo.getCdAgenda() <= 0)
				throw new IllegalArgumentException("Codigo da agenda nao informado");

			if (compromissoVo.getData() == null)
				throw new IllegalArgumentException("Data do compromisso nao informada");

			if (compromissoVo.getHora() == null)
				throw new IllegalArgumentException("Hora do compromisso nao informada");
			
			validarPeriodoAgenda(compromissoVo);
			dao.insertCompromisso(compromissoVo);

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public void atualizarCompromisso(CompromissoVo compromissoVo) {
		try {
			if (compromissoVo.getRowid() == null || compromissoVo.getRowid().isEmpty())
				throw new IllegalArgumentException("Codigo do compromisso nao informado");

			if (compromissoVo.getCdFuncionario() == null || compromissoVo.getCdFuncionario() <= 0)
				throw new IllegalArgumentException("Codigo do funcionario nao informado");

			if (compromissoVo.getCdAgenda() == null || compromissoVo.getCdAgenda() <= 0)
				throw new IllegalArgumentException("Codigo da agenda nao informado");

			if (compromissoVo.getData() == null)
				throw new IllegalArgumentException("Data do compromisso nao informada");

			if (compromissoVo.getHora() == null)
				throw new IllegalArgumentException("Hora do compromisso nao informada");
			
			validarPeriodoAgenda(compromissoVo);
			dao.updateCompromisso(compromissoVo);

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public CompromissoVo buscarCompromissoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException("Foi informado caracter no lugar de um numero");
		}
	}

	public void excluirCompromisso(String rowid) {
		try {
			if (rowid == null || rowid.isEmpty())
				throw new IllegalArgumentException("Codigo do compromisso nao informado");
			dao.deleteCompromisso(rowid);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusao do compromisso");
		}
	}

	private void validarPeriodoAgenda(CompromissoVo compromissoVo) {
		try {
			if (compromissoVo.getCdAgenda() == null || compromissoVo.getCdAgenda() <= 0)
				throw new IllegalArgumentException("Codigo da agenda nao informado");
			
			if (compromissoVo.getHora() == null)
				throw new IllegalArgumentException("Hora do compromisso nao informada");

			AgendaBusiness agendaBusiness = new AgendaBusiness();

			AgendaVo agendaVo = agendaBusiness.buscarAgendaPor(String.valueOf(compromissoVo.getCdAgenda()));
			
			if (agendaVo == null) {
				throw new IllegalArgumentException("Agenda nao encontrada");
			}

			LocalTime horaCompromisso = compromissoVo.getHora();

			LocalTime inicioManha = LocalTime.of(6, 0);
			LocalTime inicioTarde = LocalTime.of(12, 0);
			LocalTime fimTarde = LocalTime.of(18, 0);

			switch (agendaVo.getPeriodo()) {
			case 1:
				if (horaCompromisso.isBefore(inicioManha) || !horaCompromisso.isBefore(inicioTarde))
					throw new IllegalArgumentException("Horario fora do periodo da manha");
				
				break;

			case 2:
				if (horaCompromisso.isBefore(inicioTarde) || horaCompromisso.isAfter(fimTarde)) 
					throw new IllegalArgumentException("Horario fora do periodo da tarde");
				
				break;

			case 3:
				if (horaCompromisso.isBefore(inicioManha) || horaCompromisso.isAfter(fimTarde))

					throw new IllegalArgumentException("Horario fora do periodo disponivel");
				
				break;
				
			default:
				throw new IllegalArgumentException("Periodo da agenda invalido");
			}
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

	}
}