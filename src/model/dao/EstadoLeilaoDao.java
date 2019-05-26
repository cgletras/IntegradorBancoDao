package model.dao;

import java.util.List;

import model.entities.EstadoLeilao;

public interface EstadoLeilaoDao {

	void insert(EstadoLeilao obj);
	void update(EstadoLeilao obj);
	void deleteById(Integer id);
	EstadoLeilao  findById(Integer id);
	List<EstadoLeilao> findAll();
}
