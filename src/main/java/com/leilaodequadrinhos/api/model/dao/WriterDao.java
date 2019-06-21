package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.Writer;

import java.util.List;

public interface WriterDao extends DAO {

	List<Writer> findByProduct(Product obj);
	void relateWriterToProduct(Writer writer, Product product);
}