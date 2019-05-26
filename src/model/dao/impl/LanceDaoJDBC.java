package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.LanceDao;
import model.entities.Lance;

public class LanceDaoJDBC implements LanceDao {

private Connection conn;
	
	public LanceDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Lance obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Lance obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Lance findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lance> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
