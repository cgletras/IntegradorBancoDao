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
	public void insert(EstadoProduto obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(EstadoProduto obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstadoProduto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoProduto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
