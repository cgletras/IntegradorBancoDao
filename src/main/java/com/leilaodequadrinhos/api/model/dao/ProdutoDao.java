package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.EstadoProduto;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface ProdutoDao extends DAO {

	List<Produto> findAllByUser(Long UserId);
	void changeStatusProduct(Integer id, EstadoProduto estado);
}
