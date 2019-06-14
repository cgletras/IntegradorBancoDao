package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.PersonagemDao;
import model.entities.Personagem;
import model.entities.Produto;

public class PersonagemDaoJDBC implements PersonagemDao {

private Connection conn;
	
	public PersonagemDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Personagem findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Personagem "
					+ "WHERE id_personagem = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Personagem obj = new Personagem();
				obj.setIdPersonagem(rs.getInt("id_personagem"));
				obj.setNome(rs.getString("nome"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Personagem> findByProduto(Produto produto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pp.id_personagem, p.nome " + 
					"FROM personagem_produto pp INNER JOIN Personagem p " + 
					"ON pp.id_personagem = p.id_personagem " + 
					"WHERE id_produto = ?");
			
			st.setInt(1, produto.getIdProduto());
			
			rs = st.executeQuery();
			
			List<Personagem> list = new ArrayList<>();
			Map<Integer, Personagem> map = new HashMap<>();
			
			while (rs.next()) {
				Personagem obj = new Personagem();
				obj.setIdPersonagem(rs.getInt("id_personagem"));
				obj.setNome(rs.getString("nome"));
						
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Personagem> findByAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_personagem, nome "
					+ "FROM Personagem "
					+ "ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Personagem> list = new ArrayList<>();
			Map<Integer, Personagem> map = new HashMap<>();
			
			while (rs.next()) {
				Personagem obj = new Personagem();
				obj.setIdPersonagem(rs.getInt("id_personagem"));
				obj.setNome(rs.getString("nome"));
						
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insertPersonagem(Personagem obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO Personagem (nome) " + "VALUES " + "(?)",
				java.sql.Statement.RETURN_GENERATED_KEYS);

		st.setString(1, obj.getNome());
		
		int rowsAffected = st.executeUpdate();
	
		if (rowsAffected > 0) {
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				obj.setIdPersonagem(id);
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
	public void relacionarPersonagemProduto(Personagem personagem, Produto produto) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO personagem_produto (id_personagem, id_produto) " + "VALUES " + "(?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, personagem.getIdPersonagem());
			st.setInt(2, produto.getIdProduto());
			
			int rowsAffected = st.executeUpdate();
		
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
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
}
