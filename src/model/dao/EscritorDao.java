package model.dao;

import java.util.List;

import model.entities.Escritor;

public interface EscritorDao {

	void insert(Escritor obj);
	void update(Escritor obj);
	void deleteById(Integer id);
	Escritor  findById(Integer id);
	List<Escritor> findAll();
}
