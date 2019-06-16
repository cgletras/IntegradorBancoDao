package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.EscritorDao;
import com.leilaodequadrinhos.api.model.entities.Escritor;
import com.leilaodequadrinhos.api.model.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EscritorDAO implements EscritorDao {

private Connection conn;
	
	public EscritorDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Escritor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Escritor "
					+ "WHERE id_escritor = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Escritor obj = new Escritor();
				obj.setIdEscritor(rs.getInt("id_escritor"));
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
	public List<Escritor> findByProduto(Produto produto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT ep.id_escritor, e.nome " + 
					"FROM escritor_produto ep INNER JOIN Escritor e " + 
					"ON ep.id_escritor = e.id_escritor " + 
					"WHERE id_produto = ?");
			
			st.setInt(1, produto.getIdProduto());
			
			rs = st.executeQuery();
			
			List<Escritor> list = new ArrayList<>();
			Map<Integer, Escritor> map = new HashMap<>();
			
			while (rs.next()) {
				Escritor obj = new Escritor();
				obj.setIdEscritor(rs.getInt("id_escritor"));
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
	public void insertEscritor(Escritor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Escritor (nome) " + "VALUES " + "(?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			
			int rowsAffected = st.executeUpdate();
		
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdEscritor(id);
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
	public List<Escritor> findByAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_escritor, nome "
					+ "FROM Escritor "
					+ "ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Escritor> list = new ArrayList<>();
			Map<Integer, Escritor> map = new HashMap<>();
			
			while (rs.next()) {
				Escritor obj = new Escritor();
				obj.setIdEscritor(rs.getInt("id_escritor"));
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
	public void relacionarEscritorProduto(Escritor escritor, Produto produto) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO escritor_produto (id_escritor, id_produto) " + "VALUES " + "(?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, escritor.getIdEscritor());
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
