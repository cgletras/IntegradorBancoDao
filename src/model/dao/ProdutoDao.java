package model.dao;

import java.util.List;

import model.entities.Produto;

public interface ProdutoDao {

	Produto findById(Integer id);
	List<Produto> findAll();
}
