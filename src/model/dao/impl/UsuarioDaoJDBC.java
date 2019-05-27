package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Leilao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Usuario (nome, email, senha, data_nascimento, ativo) " + "VALUES " + "(?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getEmail());
			st.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setBoolean(5, obj.isAtivo());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdUsuario(id);
				}
				DB.closeResultSet(rs);
			} 
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario "  
					+ "SET Name = ?, Email = ?, senha = ?, data_nascimento = ?, ativo = ? " 
					+ "WHERE Id = ? ");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getSenha());
			st.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setBoolean(5, obj.isAtivo());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdUsuario(id);
				}
				DB.closeResultSet(rs);
			} 
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Usuario findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inactivate(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> findByLeilao(Leilao obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
