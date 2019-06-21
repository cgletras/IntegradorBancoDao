package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.ProductStatus;

import java.util.List;

public interface ProductStatusDao {

	ProductStatus findById(Long id);
	List<ProductStatus> findAll();
}