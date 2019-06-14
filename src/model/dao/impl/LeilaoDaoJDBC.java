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
import model.dao.DaoFactory;
import model.dao.EstadoLeilaoDao;
import model.dao.LeilaoDao;
import model.dao.ProdutoDao;
import model.dao.UserDao;
import model.entities.EstadoLeilao;
import model.entities.Leilao;
import model.entities.Produto;
import model.entities.User;

public class LeilaoDaoJDBC implements LeilaoDao {

private Connection conn;
	
	public LeilaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Leilao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Leilao (data_inicio, duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

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
					int id = rs.getInt(1);
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
	public void update(Leilao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Leilao " + 
					"SET data_inicio= ?, duracao= ?, valor_inicial= ?, valor_atual= ?, lance_padrao= ?, id_estado_leilao= ?, id_usuario= ?, id_produto= ? " + 
					"WHERE id_leilao= ?");

			st.setDate(1, new java.sql.Date(obj.getDataInicio().getTime()));
			st.setInt(2, obj.getDuracao());
			st.setDouble(3, obj.getValorInicial());
			st.setDouble(4, obj.getValorInicial());
			st.setDouble(5, obj.getLancePadrao());
			st.setInt(6, obj.getEstado().getIdEstadoLeilao());
			st.setInt(7, obj.getUser().getUserID());
			st.setInt(8, obj.getProduto().getIdProduto());
			st.setInt(9, obj.getIdLeilao());	
			
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
					"SET valor_atual= ? " + 
					"WHERE id_leilao= ?");

			st.setDouble(1, obj.getValorAtual()+obj.getLancePadrao());
			st.setInt(2, obj.getIdLeilao());
									
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
	public Leilao findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Leilao "
					+ "WHERE id_leilao = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Leilao obj = new Leilao();
							
				obj.setIdLeilao(rs.getInt("id_leilao"));
				obj.setDataInicio(new java.sql.Date(rs.getDate("data_inicio").getTime()));
				obj.setDuracao(rs.getInt("duracao"));
				obj.setValorInicial(rs.getDouble("valor_inicial"));
				obj.setValorAtual(rs.getDouble("valor_atual"));
				obj.setLancePadrao(rs.getDouble("lance_padrao"));
				
				EstadoLeilaoDao estadoleilaoDao = DaoFactory.createEstadoLeilaoDao();
				EstadoLeilao estadoLeilao = estadoleilaoDao.findById(rs.getInt("id_estado_leilao"));
				obj.setEstado(estadoLeilao);
				
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
				obj.setUser(user);
								
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("id_produto"));
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
							
				obj.setIdLeilao(rs.getInt("id_leilao"));
				obj.setDataInicio(new java.sql.Date(rs.getDate("data_inicio").getTime()));
				obj.setDuracao(rs.getInt("duracao"));
				obj.setValorInicial(rs.getDouble("valor_inicial"));
				obj.setValorAtual(rs.getDouble("valor_atual"));
				obj.setLancePadrao(rs.getDouble("lance_padrao"));
				
				EstadoLeilaoDao estadoleilaoDao = DaoFactory.createEstadoLeilaoDao();
				EstadoLeilao estadoLeilao = estadoleilaoDao.findById(rs.getInt("id_estado_leilao"));
				obj.setEstado(estadoLeilao);
				
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
				obj.setUser(user);
								
				ProdutoDao produtoDao = DaoFactory.createProdutoDao();
				Produto produto = produtoDao.findById(rs.getInt("id_produto"));
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
							
				leilao.setIdLeilao(rs.getInt("id_leilao"));
				leilao.setDataInicio(new java.sql.Date(rs.getDate("data_inicio").getTime()));
				leilao.setDuracao(rs.getInt("duracao"));
				leilao.setValorInicial(rs.getDouble("valor_inicial"));
				leilao.setValorAtual(rs.getDouble("valor_atual"));
				leilao.setLancePadrao(rs.getDouble("lance_padrao"));
				
				EstadoLeilaoDao estadoleilaoDao = DaoFactory.createEstadoLeilaoDao();
				EstadoLeilao estadoLeilao = estadoleilaoDao.findById(rs.getInt("id_estado_leilao"));
				leilao.setEstado(estadoLeilao);
				
				UserDao userDao = DaoFactory.createUsuarioDao();
				User usuario = userDao.findById(rs.getInt("id_usuario"));
				leilao.setUser(usuario);
								
				ProdutoDao produtoDao = DaoFactory.createProdutoDao();
				Produto produto = produtoDao.findById(rs.getInt("id_produto"));
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
