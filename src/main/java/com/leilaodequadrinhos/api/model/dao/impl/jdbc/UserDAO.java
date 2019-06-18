package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO implements UserDao {

	Connection conn = DB.getConnection();

	@Override
	public User findById(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Usuario "
					+ "WHERE id_usuario = ?");
			
			st.setLong(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				User obj = new User();
				obj.setUserID(rs.getInt("id_usuario"));
				obj.setName(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("senha"));
				obj.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
				obj.setStatus(rs.getBoolean("ativo"));
				
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
	public List findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_usuario, nome, email, senha, data_nascimento, ativo "
							+ "FROM Usuario "
							+ "ORDER BY nome");

			rs = st.executeQuery();

			List<User> list = new ArrayList<>();
			Map<Integer, User> map = new HashMap<>();

			while (rs.next()) {
				User obj = new User();
				obj.setUserID(rs.getInt("id_usuario"));
				obj.setName(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("senha"));
				obj.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
				obj.setStatus(rs.getBoolean("ativo"));

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
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT COUNT(id_usuario) count "
							+ "FROM Usuario ");

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

	@Override
	public void deleteById(Long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET ativo= ? " + 
					"WHERE id_usuario= ?");
			
			st.setBoolean(1, false);
			st.setLong(2, id);
								
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Object entity) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET nome= ?, email= ?, senha= ?, data_nascimento= ?, ativo= ? " + 
					"WHERE id_usuario= ?");
			
			User obj = (User) entity;
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getPassword());
			st.setDate(4, new java.sql.Date(obj.getDateOfBirth().getTime()));
			st.setBoolean(5, obj.isStatus());
			st.setInt(6, obj.getUserID());
						
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void insert(User obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Usuario (nome, email, senha, data_nascimento, ativo) " + "VALUES " + "(?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getPassword());
			st.setDate(4, new java.sql.Date(obj.getDateOfBirth().getTime()));
			st.setBoolean(5, obj.isStatus());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setUserID(id);
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
	public void activate(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET ativo= ? " + 
					"WHERE id_usuario= ?");
			
			st.setBoolean(1, true);
			st.setInt(2, id);;
									
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public User findByEmail(String email) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Usuario "
					+ "WHERE email = ?");
			
			st.setString(1, email);
			rs = st.executeQuery();
			
			if (rs.next()) {
				User obj = new User();
				obj.setUserID(rs.getInt("id_usuario"));
				obj.setName(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("senha"));
				obj.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
				obj.setStatus(rs.getBoolean("ativo"));
				
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
}
