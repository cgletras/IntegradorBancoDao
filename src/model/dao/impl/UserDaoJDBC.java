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
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao {

private Connection conn;
	
	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
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
			st.setString(3, obj.getEmail());
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
	public void update(User obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET nome= ?, email= ?, senha= ?, data_nascimento= ?, ativo= ? " + 
					"WHERE id_usuario= ?");

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
	public User findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Usuario "
					+ "WHERE id_usuario = ?");
			
			st.setInt(1, id);
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
	public List<User> findAll() {
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

	@Override
	public void inactivate(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET ativo= ? " + 
					"WHERE id_usuario= ?");
			
			st.setBoolean(1, false);
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
}
