package model.dao;

import java.util.List;

import model.entities.Leilao;

public interface LeilaoDao {

	void insert(Leilao obj);
	void update(Leilao obj);
	void deleteById(Integer id);
	Leilao  findById(Integer id);
	List<Leilao> findAll();
}
