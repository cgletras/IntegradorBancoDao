package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;

import java.util.List;

public class ProgramEstadoProduto {

	public static void main(String[] args) {
		
		
		
		//Find ProductStatus by id
		int id = 1;
		
//OK	estadoProdutoPorId(id);
		
		//Lista todos os EstadosLeiloes.
		
// ok listarEstadosProduto();
		
				
	}

	public static List<ProductStatus> listarEstadosProduto() {
		ProductStatusDao productStatusDao = DaoFactory.createEstadoProdutoDao();
		return productStatusDao.findAll();
	}

	public static ProductStatus estadoProdutoPorId(int id) {
		ProductStatusDao productStatusDao = DaoFactory.createEstadoProdutoDao();
		return productStatusDao.findById(id);
	}
}
