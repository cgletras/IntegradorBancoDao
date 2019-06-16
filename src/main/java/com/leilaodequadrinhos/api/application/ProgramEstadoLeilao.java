package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.EstadoLeilaoDao;
import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;

import java.util.List;

public class ProgramEstadoLeilao {

	public static void main(String[] args) {
		
		
		
		//Find EstadoLeilao by id
		int id = 1;
		
//OK	estadoLeilaoPorId(id);
		
		//Lista todos os EstadosLeiloes.
		
//OK	listarEstadosLeilao();
		
				
	}

	public static List<EstadoLeilao> listarEstadosLeilao() {
		EstadoLeilaoDao estadoLeilaoDao = DaoFactory.createEstadoLeilaoDao();
		return estadoLeilaoDao.findAll();
	}

	public static EstadoLeilao estadoLeilaoPorId(int id) {
		EstadoLeilaoDao estadoLeilaoDao = DaoFactory.createEstadoLeilaoDao();
		return estadoLeilaoDao.findById(id);
	}
}
