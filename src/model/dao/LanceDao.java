package model.dao;

import java.util.List;

import model.entities.Lance;

public interface LanceDao {

	void insert(Lance obj);
	void update(Lance obj);
	void deleteById(Integer id);
	Lance  findById(Integer id);
	List<Lance> findAll();
}
