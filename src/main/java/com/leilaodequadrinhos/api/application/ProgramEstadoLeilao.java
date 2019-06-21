package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.AuctionStatusDao;
import com.leilaodequadrinhos.api.model.entities.AuctionStatus;

import java.util.List;

public class ProgramEstadoLeilao {

	public static void main(String[] args) {
		
		
		
		//Find AuctionStatus by id
		int id = 1;
		
//OK	estadoLeilaoPorId(id);
		
		//Lista todos os EstadosLeiloes.
		
//OK	listarEstadosLeilao();
		
				
	}

	public static List<AuctionStatus> listarEstadosLeilao() {
		AuctionStatusDao auctionStatusDao = DaoFactory.createEstadoLeilaoDao();
		return auctionStatusDao.findAll();
	}

	public static AuctionStatus estadoLeilaoPorId(int id) {
		AuctionStatusDao auctionStatusDao = DaoFactory.createEstadoLeilaoDao();
		return auctionStatusDao.findById(id);
	}
}
