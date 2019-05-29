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
import model.dao.LeilaoDao;
import model.dao.UsuarioDao;
import model.entities.EstadoLeilao;
import model.entities.Leilao;
import model.entities.Produto;
import model.entities.Usuario;

public class LeilaoDaoJDBC implements LeilaoDao {

private Connection conn;
	
	public LeilaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Leilao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Leilao obj) {
		// TODO Auto-generated method stub

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
				
				UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
				Usuario usuario = usuarioDao.findById(rs.getInt("id_usuario"));
				obj.setUsuario(usuario);
								
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Leilao> findByUser(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
