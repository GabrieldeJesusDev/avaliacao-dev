package br.com.soc.sistema.vo;

public class AgendaVo {
	
	private String rowid;
	private String nome;
	private Integer periodo;
	
	public AgendaVo() {}

	public AgendaVo(String rowid, String nome, Integer periodo) {
		this.rowid = rowid;
		this.nome = nome;
		this.periodo = periodo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public String getRowid() {
		return rowid;
	}

	
}