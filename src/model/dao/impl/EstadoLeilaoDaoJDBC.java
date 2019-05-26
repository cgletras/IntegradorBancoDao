package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.EstadoLeilaoDao;
import model.entities.EstadoLeilao;

public class EstadoLeilaoDaoJDBC implements EstadoLeilaoDao {

private Connection conn;
	
	public EstadoLeilaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(EstadoLeilao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(EstadoLeilao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstadoLeilao findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoLeilao> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
