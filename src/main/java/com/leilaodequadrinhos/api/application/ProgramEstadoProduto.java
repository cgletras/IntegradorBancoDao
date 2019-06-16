package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.entities.EstadoProduto;

import java.util.List;

public class ProgramEstadoProduto {

	public static void main(String[] args) {
		
		
		
		//Find EstadoProduto by id
		int id = 1;
		
//OK	estadoProdutoPorId(id);
		
		//Lista todos os EstadosLeiloes.
		
// ok listarEstadosProduto();
		
				
	}

	public static List<EstadoProduto> listarEstadosProduto() {
		EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
		return estadoProdutoDao.findAll();
	}

	public static EstadoProduto estadoProdutoPorId(int id) {
		EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
		return estadoProdutoDao.findById(id);
	}
}
