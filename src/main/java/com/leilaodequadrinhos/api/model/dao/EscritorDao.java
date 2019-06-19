package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Escritor;
import com.leilaodequadrinhos.api.model.entities.Produto;

import java.util.List;

public interface EscritorDao extends DAO {

	List<Escritor> findByProduto(Produto obj);
	void relacionarEscritorProduto(Escritor escritor, Produto produto);
}
