package br.com.soc.sistema.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompromissoVo {

	private String rowid;
	private Integer cdFuncionario;
	private Integer cdAgenda;
	private LocalDate data;
	private LocalTime hora;

	public CompromissoVo() {
	}

	public CompromissoVo(String rowid, Integer cdFuncionario, Integer cdAgenda, LocalDate data, LocalTime hora) {
		this.rowid = rowid;
		this.cdFuncionario = cdFuncionario;
		this.cdAgenda = cdAgenda;
		this.data = data;
		this.hora = hora;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public Integer getCdFuncionario() {
		return cdFuncionario;
	}

	public void setCdFuncionario(Integer cdFuncionario) {
		this.cdFuncionario = cdFuncionario;
	}

	public Integer getCdAgenda() {
		return cdAgenda;
	}

	public void setCdAgenda(Integer cdAgenda) {
		this.cdAgenda = cdAgenda;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "CompromissoVo [rowid=" + rowid + ", cdFuncionario=" + cdFuncionario + ", cdAgenda=" + cdAgenda
				+ ", data=" + data + ", hora=" + hora + "]";
	}

}
