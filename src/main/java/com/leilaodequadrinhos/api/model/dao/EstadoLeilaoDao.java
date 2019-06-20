package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;

import java.util.List;

public interface EstadoLeilaoDao {

	EstadoLeilao  findById(Long id);
	List<EstadoLeilao> findAll();
}
