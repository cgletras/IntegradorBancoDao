package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.EstadoProdutoDao;
import model.dao.ProductDao;
import model.dao.UserDao;
import model.entities.EstadoProduto;
import model.entities.Product;
import model.entities.User;

public class ProductDaoJDBC implements ProductDao {

private Connection conn;
	
	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Produto "
					+ "WHERE id_produto = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Product obj = new Product();
				obj.setIdProduto(rs.getInt("id_produto"));
				obj.setEditora(rs.getString("editora"));
				obj.setTitulo(rs.getString("titulo"));
				obj.setFormatoDoQuadrinho(rs.getString("formato_do_quadrinho"));
				obj.setNumeroPaginas(rs.getInt("numero_paginas"));
				obj.setPeso(rs.getInt("peso"));
				obj.setCapaImagem(rs.getString("capa_imagem"));

				EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
				EstadoProduto estadoProduto = estadoProdutoDao.findById(rs.getInt("id_estado_produto"));
				obj.setEstado(estadoProduto);
				
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
				obj.setUser(user);
				
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
	public List<Product> findAllByUser(User usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Produto "
					+ "WHERE id_usuario = ?");
			
			st.setInt(1, usuario.getUserID());
			rs = st.executeQuery();
			List<Product> products = new ArrayList<Product>();
			
			while (rs.next()) {
				Product obj = new Product();
				obj.setIdProduto(rs.getInt("id_produto"));
				obj.setEditora(rs.getString("editora"));
				obj.setTitulo(rs.getString("titulo"));
				obj.setFormatoDoQuadrinho(rs.getString("formato_do_quadrinho"));
				obj.setNumeroPaginas(rs.getInt("numero_paginas"));
				obj.setPeso(rs.getInt("peso"));
				obj.setCapaImagem(rs.getString("capa_imagem"));

				EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
				EstadoProduto estadoProduto = estadoProdutoDao.findById(rs.getInt("id_estado_produto"));
				obj.setEstado(estadoProduto);
				
				UserDao userDao = DaoFactory.createUsuarioDao();
				User user = userDao.findById(rs.getInt("id_usuario"));
				obj.setUser(user);
				
				products.add(obj);
			}
			return products;
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
	public void insertProduct(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Produto (editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, id_estado_produto, id_usuario) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getEditora());
			st.setString(2, obj.getTitulo());
			st.setString(3, obj.getFormatoDoQuadrinho());
			st.setInt(4, obj.getNumeroPaginas());
			st.setInt(5, obj.getPeso());
			st.setString(6, obj.getCapaImagem());
			st.setInt(7, obj.getEstado().getIdEstadoProduto());
			st.setInt(8, obj.getUser().getUserID());
			
			int rowsAffected = st.executeUpdate();
		
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdProduto(id);
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
	public void changeStatusProduct(Integer id, EstadoProduto estado) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Produto " + 
					"SET id_estado_produto= ? " + 
					"WHERE id_produto= ?");
			
			st.setInt(1, estado.getIdEstadoProduto());
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

	@Override
	public void updateProduct(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Produto "
					+ "SET editora= ?, titulo= ?, formato_do_quadrinho= ?, numero_paginas= ?, peso= ?, capa_imagem= ?, id_estado_produto= ?, id_usuario= ? " 
					+ "WHERE id_produto= ?",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getEditora());
			st.setString(2, obj.getTitulo());
			st.setString(3, obj.getFormatoDoQuadrinho());
			st.setInt(4, obj.getNumeroPaginas());
			st.setInt(5, obj.getPeso());
			st.setString(6, obj.getCapaImagem());
			st.setInt(7, obj.getEstado().getIdEstadoProduto());
			st.setInt(8, obj.getUser().getUserID());
			st.setInt(9, obj.getIdProduto());
			
			int rowsAffected = st.executeUpdate();
		
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdProduto(id);
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
}
