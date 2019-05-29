package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import db.DB;
import db.DbException;
import model.dao.LanceDao;
import model.entities.Lance;
import model.entities.Leilao;
import model.entities.Usuario;

public class LanceDaoJDBC implements LanceDao {

	private Connection conn;

	public LanceDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Lance obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Lance (valor_lance, data_lance, id_leilao, id_usuario) " + "VALUES " + "(?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setDouble(1, obj.getValorLance());
			st.setDate(2, new java.sql.Date(obj.getDataLance().getTime()));
			st.setInt(3, obj.getLeilao().getIdLeilao());
			st.setInt(4, obj.getUsuario().getIdUsuario());
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
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Lance findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lance> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lance> findByUser(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lance> findByLeilao(Leilao obj) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
