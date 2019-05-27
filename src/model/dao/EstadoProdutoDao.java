package model.dao;

import java.util.List;

import model.entities.EstadoProduto;

public interface EstadoProdutoDao {

	EstadoProduto findById();
	List<EstadoProduto> findAll();
}
