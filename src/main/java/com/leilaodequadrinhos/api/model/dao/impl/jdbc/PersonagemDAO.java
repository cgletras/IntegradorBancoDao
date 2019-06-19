package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.PersonagemDao;
import com.leilaodequadrinhos.api.model.entities.Personagem;
import com.leilaodequadrinhos.api.model.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonagemDAO implements PersonagemDao {

	Connection conn = DB.getConnection();

	@Override
	public Object findById(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Personagem "
					+ "WHERE id_personagem = ?");
			
			st.setLong(1, id);
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
	public List<Personagem> findAllByProduto(Produto produto) {
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
	public List findAll() {

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
	public void insert(Object entity) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO Personagem (nome) " + "VALUES " + "(?)",
				java.sql.Statement.RETURN_GENERATED_KEYS);

		Personagem obj = (Personagem) entity;
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

	// TODO Este metodo não foi implementado pois será somente utilizado com funcionalidade utilizada na formaulação relatorio e adminsitração do site, o que nãi esta no scope atual.
	@Override
	public Long count() {
		return null;
	}

	// TODO Este metodo não foi implementado pois será somente utilizado com funcionalidade utilizada na formaulação relatorio e adminsitração do site, o que nãi esta no scope atual.
	@Override
	public void deleteById(Long id) {
	}

	@Override
	public void update(Object entity) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Personagem " +
							"SET nome= ? " +
							"WHERE id_personagem= ?");

			Personagem obj = (Personagem) entity;
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getIdPersonagem());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

}
