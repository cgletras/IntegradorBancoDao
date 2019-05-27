package model.dao;

import java.util.List;

import model.entities.EstadoLeilao;

public interface EstadoLeilaoDao {

	EstadoLeilao  findById(Integer id);
	List<EstadoLeilao> findAll();
}
