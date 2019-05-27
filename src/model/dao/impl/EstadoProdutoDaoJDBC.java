package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.EstadoProdutoDao;
import model.entities.EstadoProduto;

public class EstadoProdutoDaoJDBC implements EstadoProdutoDao {

private Connection conn;
	
	public EstadoProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public EstadoProduto findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoProduto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
