package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.EstadoProduto;

import java.util.List;

public interface EstadoProdutoDao {

	EstadoProduto findById(Long id);
	List<EstadoProduto> findAll();
}
