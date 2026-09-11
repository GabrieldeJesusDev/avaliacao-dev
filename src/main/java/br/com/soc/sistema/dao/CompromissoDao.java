package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoDao extends Dao {

	public List<CompromissoVo> findAllCompromissos() {
		StringBuilder query = new StringBuilder(
				"SELECT rowid id, cd_funcionario cdFuncionario, cd_agenda cdAgenda, data, hora FROM compromisso");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			CompromissoVo vo = null;

			List<CompromissoVo> compromissos = new ArrayList<>();
			while (rs.next()) {
				vo = new CompromissoVo();
				vo.setRowid(rs.getString("id"));
				vo.setCdFuncionario(rs.getInt("cdFuncionario"));
				vo.setCdAgenda(rs.getInt("cdAgenda"));
				vo.setData(rs.getDate("data").toLocalDate());
				vo.setHora(rs.getTime("hora").toLocalTime());

				compromissos.add(vo);
			}
			return compromissos;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public void insertCompromisso(CompromissoVo compromissoVo) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO compromisso (cd_funcionario, cd_agenda, data, hora) VALUES (?,?,?,?)");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;

			ps.setInt(i++, compromissoVo.getCdFuncionario());
			ps.setInt(i++, compromissoVo.getCdAgenda());
			ps.setDate(i++, java.sql.Date.valueOf(compromissoVo.getData()));
			ps.setTime(i++, java.sql.Time.valueOf(compromissoVo.getHora()));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCompromisso(CompromissoVo compromissoVo) {
		StringBuilder query = new StringBuilder(
				"UPDATE compromisso SET cd_funcionario = ?, cd_agenda = ?, data = ?, hora = ? WHERE rowid = ?");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;

			ps.setInt(i++, compromissoVo.getCdFuncionario());
			ps.setInt(i++, compromissoVo.getCdAgenda());
			ps.setDate(i++, java.sql.Date.valueOf(compromissoVo.getData()));
			ps.setTime(i++, java.sql.Time.valueOf(compromissoVo.getHora()));
			ps.setString(i++, compromissoVo.getRowid());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCompromisso(String rowid) {
		StringBuilder query = new StringBuilder("DELETE FROM compromisso WHERE rowid = ?");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;
			ps.setString(i++, rowid);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CompromissoVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder(
				"SELECT rowid id, cd_funcionario cdFuncionario, cd_agenda cdAgenda, data, hora FROM compromisso WHERE rowid = ?");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;

			ps.setInt(i++, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					CompromissoVo vo = new CompromissoVo();
					vo.setRowid(rs.getString("id"));
					vo.setCdFuncionario(rs.getInt("cdFuncionario"));
					vo.setCdAgenda(rs.getInt("cdAgenda"));
					vo.setData(rs.getDate("data").toLocalDate());
					vo.setHora(rs.getTime("hora").toLocalTime());

					return vo;

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}