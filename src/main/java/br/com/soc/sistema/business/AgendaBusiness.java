package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {
	
	private AgendaDao dao;
	
	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}
	
	public List<AgendaVo> tazerTodasAsAgendas(){
		return dao.findAllAgendas();
	}
	
	public void salvarAgenda(AgendaVo agendaVo) {
		try {
			if(agendaVo.getNome().isEmpty())
				throw new IllegalArgumentException("Agenda nao pode estar em branco");
			
			if(agendaVo.getPeriodo() == null)
				throw new IllegalArgumentException("Agenda deve conter um periodo");
			
			if(agendaVo.getPeriodo() < 1 || agendaVo.getPeriodo() > 3)
			    throw new IllegalArgumentException("Periodo invalido");
			
			dao.insertAgenda(agendaVo);
				
		}catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao de um registro");
		}
	}
	
	public void atualizarAgenda(AgendaVo agendaVo) {
		try {
			if (agendaVo.getRowid() == null || agendaVo.getRowid().isEmpty())
				throw new IllegalArgumentException("Codigo da agenda nao informado");
			
			if (agendaVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nao pode estar em branco");
			
			if(agendaVo.getPeriodo() == null || agendaVo.getPeriodo() < 1 || agendaVo.getPeriodo() > 3)
				throw new IllegalArgumentException("Agenda deve conter um periodo");

			
			dao.updateAgenda(agendaVo);
			
		}catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a atualizacao da agenda");
		}
	}
	
	public AgendaVo buscarAgendaPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch(NumberFormatException e) {
			throw new BusinessException("Foi informado caracter no lugar de um numero");
		}
	}
	
}
