package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.EstadoProduto;

import java.util.List;

public interface EstadoProdutoDao {

	EstadoProduto findById(Integer id);
	List<EstadoProduto> findAll();
}
