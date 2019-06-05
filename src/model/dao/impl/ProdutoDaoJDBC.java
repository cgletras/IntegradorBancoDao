package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.EstadoLeilaoDao;
import model.dao.EstadoProdutoDao;
import model.dao.ProdutoDao;
import model.dao.UsuarioDao;
import model.entities.EstadoLeilao;
import model.entities.EstadoProduto;
import model.entities.Produto;
import model.entities.Usuario;

public class ProdutoDaoJDBC implements ProdutoDao {

private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Produto findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Produto "
					+ "WHERE id_produto = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Produto obj = new Produto();
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
				
				UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
				Usuario usuario = usuarioDao.findById(rs.getInt("id_usuario"));
				obj.setUsuario(usuario);
				
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
	public List<Produto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
