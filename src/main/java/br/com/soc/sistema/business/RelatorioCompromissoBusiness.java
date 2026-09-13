package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoBusiness {
	private CompromissoDao dao;
	
	public RelatorioCompromissoBusiness() {
		this.dao = new CompromissoDao();
	}
	
	public List<RelatorioCompromissoVo> gerarRelatorio(LocalDate dataInicial, LocalDate dataFinal){
		
		if(dataInicial == null || dataFinal == null) {
			throw new IllegalArgumentException("Data nao pode estar em branco");
		}
		
		if(dataInicial.isAfter(dataFinal)) {
			throw new IllegalArgumentException("Data final tem que ser maior que a inicial");
		}
		
		return dao.buscarRelatorio(dataInicial, dataFinal);
	}
}
