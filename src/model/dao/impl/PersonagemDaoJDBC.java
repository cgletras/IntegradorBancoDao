package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.PersonagemDao;
import model.entities.Personagem;
import model.entities.Produto;

public class PersonagemDaoJDBC implements PersonagemDao {

private Connection conn;
	
	public PersonagemDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Personagem findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personagem> findByProduto(Produto obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
