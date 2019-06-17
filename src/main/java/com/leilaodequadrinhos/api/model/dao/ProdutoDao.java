package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.EstadoProduto;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface ProdutoDao extends DAO {
	
	void insertProduct(Produto produto);
	void updateProduct(Produto produto);
	Produto findById(Integer id);
	List<Produto> findAllByUser(User user);
	void changeStatusProduct(Integer id, EstadoProduto estado);
}
