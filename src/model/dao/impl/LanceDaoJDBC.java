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
import db.DbIntegrityException;
import model.dao.DaoFactory;
import model.dao.LanceDao;
import model.dao.LeilaoDao;
import model.dao.UserDao;
import model.entities.Lance;
import model.entities.Leilao;
import model.entities.User;

public class LanceDaoJDBC implements LanceDao {

	private Connection conn;

	public LanceDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Lance obj) {
		
		PreparedStatement st = null;
		try {
			
			conn.setAutoCommit(false);
			st = conn.prepareStatement(
					"INSERT INTO Lance (valor_lance, id_leilao, id_usuario) " + "VALUES " + "(?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setDouble(1, obj.getValorLance());
		//	st.setDate(2, new java.sql.Date(obj.getDataLance().getTime()));
			st.setInt(2, obj.getLeilao().getIdLeilao());
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
			LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
			Leilao leilao = leilaoDao.findById(obj.getLeilao().getIdLeilao());
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
	public Lance findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Lance "
					+ "WHERE id_lance = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Lance obj = new Lance();
				obj.setIdLance(rs.getInt("id_lance"));
				obj.setValorLance(rs.getDouble("valor_lance"));
				obj.setDataLance(new java.sql.Date(rs.getDate("data_lance").getTime()));
				//
				LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
				Leilao leilao = leilaoDao.findById(rs.getInt("id_leilao"));
				obj.setLeilao(leilao);
				//
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
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
				LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
				Leilao leilao = leilaoDao.findById(rs.getInt("id_leilao"));
				obj.setLeilao(leilao);
				//
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
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
	public List<Lance> findByUser(User userId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
					+ "FROM Lance "
					+ "WHERE id_usuario = ? "
					+ "ORDER BY id_lance");
			
			st.setInt(1, userId.getUserID());
			rs = st.executeQuery();
			
			List<Lance> list = new ArrayList<>();
			Map<Integer, Lance> map = new HashMap<>();
			
			while (rs.next()) {
				Lance obj = new Lance();
				obj.setIdLance(rs.getInt("id_lance"));
				obj.setValorLance(rs.getDouble("valor_lance"));
				obj.setDataLance(new java.sql.Date(rs.getDate("data_lance").getTime()));
				//
				LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
				Leilao leilao = leilaoDao.findById(rs.getInt("id_leilao"));
				obj.setLeilao(leilao);
				//
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
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
	public List<Lance> findByLeilao(Leilao leilaoId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
					+ "FROM Lance "
					+ "WHERE id_leilao = ? "
					+ "ORDER BY id_lance");
			
			st.setInt(1, leilaoId.getIdLeilao());
			rs = st.executeQuery();
			
			List<Lance> list = new ArrayList<>();
			Map<Integer, Lance> map = new HashMap<>();
			
			while (rs.next()) {
				Lance obj = new Lance();
				obj.setIdLance(rs.getInt("id_lance"));
				obj.setValorLance(rs.getDouble("valor_lance"));
				obj.setDataLance(new java.sql.Date(rs.getDate("data_lance").getTime()));
				//
				LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
				Leilao leilao = leilaoDao.findById(rs.getInt("id_leilao"));
				obj.setLeilao(leilao);
				//
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
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
	public void deleteLanceById(Integer id_lance) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"DELETE FROM Lance "
					+"WHERE "
					+"id_lance = ?");
			st.setInt(1, id_lance);
			

			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas afetadas" + rowsAffected);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
