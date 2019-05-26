package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.PersonagemDao;
import model.entities.Personagem;

public class PersonagemDaoJDBC implements PersonagemDao {

private Connection conn;
	
	public PersonagemDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Personagem obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Personagem obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Personagem findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personagem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
