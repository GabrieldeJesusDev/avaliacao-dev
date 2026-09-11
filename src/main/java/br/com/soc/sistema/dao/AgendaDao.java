package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.AgendaVo;

public class AgendaDao extends Dao {
	
	public List<AgendaVo> findAllAgendas(){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodo_disponivel periodo FROM agenda");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			AgendaVo vo = null;
			
			List<AgendaVo> agendas = new ArrayList<>();
			while(rs.next()) {
				vo = new AgendaVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));
				vo.setPeriodo(rs.getInt("periodo"));
				
				agendas.add(vo);	
			}
			return agendas;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
		
	}

	public void insertAgenda(AgendaVo agendaVo) {
		StringBuilder query = new StringBuilder("INSERT INTO agenda (nm_agenda, periodo_disponivel) VALUES (?,?)");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i=1;
			ps.setString(i++, agendaVo.getNome());
			ps.setInt(i++, agendaVo.getPeriodo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAgenda(AgendaVo agendaVo) {
		StringBuilder query = new StringBuilder("UPDATE agenda SET nm_agenda = ?, periodo_disponivel = ? WHERE rowid = ?");
		try (Connection con = getConexao();
			 PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i = 1;
			
			ps.setString(i++, agendaVo.getNome());
			ps.setInt(i++, agendaVo.getPeriodo());
			ps.setString(i, agendaVo.getRowid());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAgenda(String rowid) {
		StringBuilder query = new StringBuilder("DELETE FROM agenda WHERE rowid = ?");
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			ps.setString(i++, rowid);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public AgendaVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodo_disponivel periodo FROM agenda "
				+ "WHERE rowid = ?");
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i++, codigo);
			
			try(ResultSet rs = ps.executeQuery()){				
				if(rs.next()) {
					AgendaVo vo = new AgendaVo();
					vo = new AgendaVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
					vo.setPeriodo(rs.getInt("periodo"));
					
					return vo;

				}
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<AgendaVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodo_disponivel periodo FROM agenda ")
									.append("WHERE lower(nm_agenda) like lower(?)");
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				AgendaVo vo =  null;
				List<AgendaVo> agendas = new ArrayList<>();
				
				while(rs.next()) {
					vo = new AgendaVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
					vo.setPeriodo(rs.getInt("periodo"));
					
					agendas.add(vo);
				}
				return agendas;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
}
