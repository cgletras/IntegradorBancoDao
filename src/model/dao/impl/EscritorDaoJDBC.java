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
import model.entities.Produto;

public class EscritorDaoJDBC implements EscritorDao {

private Connection conn;
	
	public EscritorDaoJDBC(Connection conn) {
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
	public List<Escritor> findByProduto(Produto obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
