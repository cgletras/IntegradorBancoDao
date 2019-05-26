package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EscritorDao;
import model.entities.Escritor;

public class EscritorDaoJDBC implements EscritorDao {

private Connection conn;
	
	public EscritorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Escritor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Escritor "
					+ "(nome) "
					+ "VALUES "
					+ "(?)",
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
	public void update(Escritor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Escritor findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Escritor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
