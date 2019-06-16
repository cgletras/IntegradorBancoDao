package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Escritor;
import com.leilaodequadrinhos.api.model.entities.Produto;

import java.util.List;

public interface EscritorDao {

	Escritor findById(Integer id);
	List<Escritor> findByProduto(Produto obj);
	List<Escritor> findByAll();
	void insertEscritor(Escritor obj);
	void relacionarEscritorProduto(Escritor escritor, Produto produto);
}
