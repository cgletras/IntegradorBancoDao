package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.DAO;
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

public class UserDAO implements DAO {

	Connection conn = DB.getConnection();

	@Override
	public Object findById(Long id) {
		return null;
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
		return null;
	}

	@Override
	public void delete(Object entity) {

	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public void update(Object entity) {

	}
}
