package br.com.soc.sistema.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class RelatorioCompromissoVo {

	private Integer codigoFuncionario;
	private String nomeFuncionario;
	
	private Integer codigoAgenda;
	private String nomeAgenda;
	
	private LocalDate data;
	private LocalTime hora;
	
	
	public Integer getCodigoFuncionario() {
		return codigoFuncionario;
	}
	public void setCodigoFuncionario(Integer codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public Integer getCodigoAgenda() {
		return codigoAgenda;
	}
	public void setCodigoAgenda(Integer codigoAgenda) {
		this.codigoAgenda = codigoAgenda;
	}
	public String getNomeAgenda() {
		return nomeAgenda;
	}
	public void setNomeAgenda(String nomeAgenda) {
		this.nomeAgenda = nomeAgenda;
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
	
	
}
