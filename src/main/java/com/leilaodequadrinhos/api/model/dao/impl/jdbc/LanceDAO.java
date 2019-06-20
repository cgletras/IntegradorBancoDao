package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.db.DbIntegrityException;
import com.leilaodequadrinhos.api.model.dao.*;
import com.leilaodequadrinhos.api.model.entities.Lance;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanceDAO implements LanceDao {

	Connection conn = DB.getConnection();

	@Override
	public void insert(Object entity) {
		
		PreparedStatement st = null;
		try {
			
			conn.setAutoCommit(false);
			st = conn.prepareStatement(
					"INSERT INTO Lance (valor_lance, id_leilao, id_usuario) " + "VALUES " + "(?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			Lance  obj = (Lance) entity;
			st.setDouble(1, obj.getValorLance());
			st.setLong(2, obj.getLeilao().getIdLeilao());
			st.setInt(3, obj.getUser().getUserID());
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdLance(id);
				}
				DB.closeResultSet(rs);
			} 
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			
			// incrementa valor atual leilao
			LeilaoDao leilaoDao = new LeilaoDAO();
			Leilao leilao = (Leilao) leilaoDao.findById(obj.getLeilao().getIdLeilao());
			leilaoDao.updateValorAtual(leilao);
			// --
			
			conn.commit();
		} 
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Uma das opera��es falhou: "+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Falhou o rollback");
			}
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Object findById(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Lance "
					+ "WHERE id_lance = ?");
			
			st.setLong(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Lance obj = new Lance();
				obj.setIdLance(rs.getInt("id_lance"));
				obj.setValorLance(rs.getDouble("valor_lance"));
				obj.setDataLance(new java.sql.Date(rs.getDate("data_lance").getTime()));
				//
				LeilaoDao leilaoDao = new LeilaoDAO();
				Leilao leilao = (Leilao) leilaoDao.findById(rs.getLong("id_leilao"));
				obj.setLeilao(leilao);
				//
				DAO dao = new UserDAO();
				User user = (User) dao.findById(rs.getLong("id_usuario"));
				obj.setUser(user);
				
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
	public List<Lance> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
					+ "FROM Lance "
					+ "ORDER BY id_lance");
			
			rs = st.executeQuery();
			
			List<Lance> list = new ArrayList<>();
			Map<Integer, Lance> map = new HashMap<>();
			
			while (rs.next()) {
				Lance obj = new Lance();
				obj.setIdLance(rs.getInt("id_lance"));
				obj.setValorLance(rs.getDouble("valor_lance"));
				obj.setDataLance(new java.sql.Date(rs.getDate("data_lance").getTime()));
				//
				LeilaoDao leilaoDao = new LeilaoDAO();
				Leilao leilao = (Leilao) leilaoDao.findById(rs.getLong("id_leilao"));
				obj.setLeilao(leilao);
				//
				DAO dao = new UserDAO();
				User user = (User) dao.findById(rs.getLong("id_usuario"));
				obj.setUser(user);
						
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
	public Long count() {
		return null;
	}

	// TODO Este metodo não foi implementado pois será somente utilizado com funcionalidade utilizada na formaulação relatorio e adminsitração do site, o que não esta no scope atual.
	@Override
	public void deleteById(Long id) {

	}

	// TODO Este metodo não foi implementado pois será somente utilizado com funcionalidade utilizada na formaulação relatorio e adminsitração do site, o que não esta no scope atual.
	@Override
	public void update(Object entity) {

	}

	@Override
	public List<Lance> findBidByUser(Long id_user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
					+ "FROM Lance "
					+ "WHERE id_usuario = ? "
					+ "ORDER BY id_lance");
			
			st.setLong(1, id_user);
			rs = st.executeQuery();
			
			List<Lance> list = new ArrayList<>();
			Map<Integer, Lance> map = new HashMap<>();
			
			while (rs.next()) {
				Lance obj = new Lance();
				obj.setIdLance(rs.getInt("id_lance"));
				obj.setValorLance(rs.getDouble("valor_lance"));
				obj.setDataLance(new java.sql.Date(rs.getDate("data_lance").getTime()));
				//
				LeilaoDao leilaoDao = new LeilaoDAO();
				Leilao leilao = (Leilao) leilaoDao.findById(rs.getLong("id_leilao"));
				obj.setLeilao(leilao);
				//
				DAO dao = new UserDAO();
				User user = (User) dao.findById(rs.getLong("id_usuario"));
				obj.setUser(user);
						
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
	public List<Lance> findBidLeilao(Long id_leilao) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
					+ "FROM Lance "
					+ "WHERE id_leilao = ? "
					+ "ORDER BY id_lance");
			
			st.setLong(1, id_leilao);
			rs = st.executeQuery();
			
			List<Lance> list = new ArrayList<>();
			Map<Integer, Lance> map = new HashMap<>();
			
			while (rs.next()) {
				Lance obj = new Lance();
				obj.setIdLance(rs.getInt("id_lance"));
				obj.setValorLance(rs.getDouble("valor_lance"));
				obj.setDataLance(new java.sql.Date(rs.getDate("data_lance").getTime()));
				//
				LeilaoDao leilaoDao = new LeilaoDAO();
				Leilao leilao = (Leilao) leilaoDao.findById(rs.getLong("id_leilao"));
				obj.setLeilao(leilao);
				//
				DAO dao = new UserDAO();
				User user = (User) dao.findById(rs.getLong("id_usuario"));
				obj.setUser(user);
						
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
	public Long BidCount(Long id_leilao) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT COUNT(id_lance) count "
							+ "FROM Lance "
							+ "WHERE id_leilao = ? ");

			st.setLong(1, id_leilao);
			rs = st.executeQuery();

			Long count =null;
			Map<Integer, User> map = new HashMap<>();

			while (rs.next()) {
				count = rs.getLong("count");
			}
			return count;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
