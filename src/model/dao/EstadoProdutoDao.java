package model.dao;

import java.util.List;

import model.entities.EstadoProduto;

public interface EstadoProdutoDao {

	void insert(EstadoProduto obj);
	void update(EstadoProduto obj);
	void deleteById(Integer id);
	EstadoProduto  findById(Integer id);
	List<EstadoProduto> findAll();
}
