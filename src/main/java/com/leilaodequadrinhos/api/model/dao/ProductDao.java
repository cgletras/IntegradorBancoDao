package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.ProductStatus;
import com.leilaodequadrinhos.api.model.entities.Product;

import java.util.List;

public interface ProductDao extends DAO {

	List<Product> findAllByUser(Long UserId);
	void changeStatusProduct(Integer id, ProductStatus productStatus);
}