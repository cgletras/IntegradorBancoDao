package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.*;
import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeilaoDAO implements LeilaoDao {

	Connection conn = DB.getConnection();

	@Override
	public void insert(Object entity) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Leilao (data_inicio, duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			Leilao obj = (Leilao) entity;
			st.setDate(1, new java.sql.Date(obj.getDataInicio().getTime()));
			st.setInt(2, obj.getDuracao());
			st.setDouble(3, obj.getValorInicial());
			st.setDouble(4, obj.getValorInicial());
			st.setDouble(5, obj.getLancePadrao());
			st.setInt(6, obj.getEstado().getIdEstadoLeilao());
			st.setInt(7, obj.getUser().getUserID());
			System.out.println(obj.getProduto());
			st.setInt(8, obj.getProduto().getIdProduto());
			
			int rowsAffected = st.executeUpdate();
		
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					Long id = rs.getLong(1);
					obj.setIdLeilao(id);
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
	public void update(Object entity) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Leilao " + 
					"SET data_inicio= ?, duracao= ?, valor_inicial= ?, valor_atual= ?, lance_padrao= ?, id_estado_leilao= ?, id_usuario= ?, id_produto= ? " + 
					"WHERE id_leilao= ?");

			Leilao obj = (Leilao) entity;
			st.setDate(1, new java.sql.Date(obj.getDataInicio().getTime()));
			st.setInt(2, obj.getDuracao());
			st.setDouble(3, obj.getValorInicial());
			st.setDouble(4, obj.getValorInicial());
			st.setDouble(5, obj.getLancePadrao());
			st.setInt(6, obj.getEstado().getIdEstadoLeilao());
			st.setInt(7, obj.getUser().getUserID());
			st.setInt(8, obj.getProduto().getIdProduto());
			st.setLong(9, obj.getIdLeilao());
			
			st.executeUpdate();
			System.out.println("Leilao atualizado com sucesso");
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void updateValorAtual(Leilao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Leilao " + 
					"SET valor_atual= (valor_atual + ?) " +
					"WHERE id_leilao= ?");

			st.setDouble(1, obj.getLancePadrao());
			st.setLong(2, obj.getIdLeilao());
									
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
	public Object findById(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Leilao "
					+ "WHERE id_leilao = ?");
			
			st.setLong(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Leilao obj = new Leilao();
							
				obj.setIdLeilao(rs.getLong("id_leilao"));
				obj.setDataInicio(new java.sql.Date(rs.getDate("data_inicio").getTime()));
				obj.setDuracao(rs.getInt("duracao"));
				obj.setValorInicial(rs.getDouble("valor_inicial"));
				obj.setValorAtual(rs.getDouble("valor_atual"));
				obj.setLancePadrao(rs.getDouble("lance_padrao"));

				EstadoLeilaoDao estadoleilaoDao = new EstadoLeilaoDAO();
				EstadoLeilao estadoLeilao = estadoleilaoDao.findById(rs.getLong("id_estado_leilao"));
				obj.setEstado(estadoLeilao);
				
				DAO dao = new UserDAO();
				User user = (User) dao.findById(rs.getLong("id_usuario"));
				obj.setUser(user);

				DAO dao2 = new ProdutoDAO();
				Produto produto = (Produto) dao2.findById(rs.getLong("id_produto"));
				obj.setProduto(produto);
				
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
	public List<Leilao> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_leilao, data_inicio, duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto " 
					+ "FROM Leilao ");
			
			rs = st.executeQuery();
			
			List<Leilao> list = new ArrayList<>();
			Map<Integer, Leilao> map = new HashMap<>();
			
			while (rs.next()) {
				Leilao obj = new Leilao();
							
				obj.setIdLeilao(rs.getLong("id_leilao"));
				obj.setDataInicio(new java.sql.Date(rs.getDate("data_inicio").getTime()));
				obj.setDuracao(rs.getInt("duracao"));
				obj.setValorInicial(rs.getDouble("valor_inicial"));
				obj.setValorAtual(rs.getDouble("valor_atual"));
				obj.setLancePadrao(rs.getDouble("lance_padrao"));

				EstadoLeilaoDao estadoleilaoDao = new EstadoLeilaoDAO();
				EstadoLeilao estadoLeilao = estadoleilaoDao.findById(rs.getLong("id_estado_leilao"));
				obj.setEstado(estadoLeilao);

				DAO dao = new UserDAO();
				User user = (User) dao.findById(rs.getLong("id_usuario"));
				obj.setUser(user);

				DAO dao2 = new ProdutoDAO();
				Produto produto = (Produto) dao2.findById(rs.getLong("id_produto"));
				obj.setProduto(produto);
								
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
	// TODO Este metodo não foi implementado pois será somente utilizado com funcionalidade utilizada na formaulação relatorio e adminsitração do site, o que nãi esta no scope atual.
	@Override
	public Long count() {
		return null;
	}

	@Override
	public void deleteById(Long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Leilao " +
							"SET id_estado_leilao= ? " +
							"WHERE id_leilao= ?");

			st.setInt(1, 2);
			st.setLong(2, id);;

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
	public List<Leilao> findByUser(User user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_leilao, data_inicio, duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto " 
					+ "FROM Leilao "
					+ "WHERE id_usuario = ?");
			
			st.setInt(1, user.getUserID());
			rs = st.executeQuery();
			
			List<Leilao> list = new ArrayList<>();
			Map<Integer, Leilao> map = new HashMap<>();
			
			while (rs.next()) {
				Leilao leilao = new Leilao();
							
				leilao.setIdLeilao(rs.getLong("id_leilao"));
				leilao.setDataInicio(new java.sql.Date(rs.getDate("data_inicio").getTime()));
				leilao.setDuracao(rs.getInt("duracao"));
				leilao.setValorInicial(rs.getDouble("valor_inicial"));
				leilao.setValorAtual(rs.getDouble("valor_atual"));
				leilao.setLancePadrao(rs.getDouble("lance_padrao"));
				
				EstadoLeilaoDao estadoleilaoDao = new EstadoLeilaoDAO();
				EstadoLeilao estadoLeilao = estadoleilaoDao.findById(rs.getLong("id_estado_leilao"));
				leilao.setEstado(estadoLeilao);

				DAO dao = new UserDAO();
				user = (User) dao.findById(rs.getLong("id_usuario"));
				leilao.setUser(user);

				DAO dao2 = new ProdutoDAO();
				Produto produto = (Produto) dao2.findById(rs.getLong("id_produto"));
				leilao.setProduto(produto);
								
				list.add(leilao);
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
	public void changeStatusLeilao(Integer id, EstadoLeilao estado) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Leilao " + 
					"SET id_estado_leilao= ? " + 
					"WHERE id_leilao= ?");
			
			st.setInt(1, estado.getIdEstadoLeilao());
			st.setInt(2, id);
									
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
