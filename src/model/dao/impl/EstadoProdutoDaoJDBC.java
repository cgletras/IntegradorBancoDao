package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EstadoProdutoDao;
import model.entities.EstadoLeilao;
import model.entities.EstadoProduto;

public class EstadoProdutoDaoJDBC implements EstadoProdutoDao {

private Connection conn;
	
	public EstadoProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<EstadoProduto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstadoProduto findById(Integer id) {

			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement(
						"SELECT * FROM Estado_produto "
						+ "WHERE id_estado_produto = ?");
				
				st.setInt(1, id);
				rs = st.executeQuery();
				
				if (rs.next()) {
					EstadoProduto obj = new EstadoProduto();
					obj.setIdEstadoProduto(rs.getInt("id_estado_produto"));
					obj.setEstado(rs.getString("estado"));
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
