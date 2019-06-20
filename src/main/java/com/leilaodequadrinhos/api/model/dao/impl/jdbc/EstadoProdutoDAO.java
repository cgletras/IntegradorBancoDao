package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.entities.EstadoProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstadoProdutoDAO implements EstadoProdutoDao {

	Connection conn = DB.getConnection();

	@Override
	public List<EstadoProduto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_estado_produto, estado "
					+ "FROM Estado_produto "
					+ "ORDER BY estado");
			
			rs = st.executeQuery();
			
			List<EstadoProduto> list = new ArrayList<>();
			Map<Integer, EstadoProduto> map = new HashMap<>();
			
			while (rs.next()) {
				EstadoProduto obj = new EstadoProduto();
				obj.setIdEstadoProduto(rs.getInt("id_estado_produto"));
				obj.setEstado(rs.getString("estado"));
				
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
	public EstadoProduto findById(Long id) {

			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement(
						"SELECT * FROM Estado_produto "
						+ "WHERE id_estado_produto = ?");
				
				st.setLong(1, id);
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
