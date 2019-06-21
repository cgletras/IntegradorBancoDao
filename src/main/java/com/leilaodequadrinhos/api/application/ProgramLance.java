package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.BidDao;
import com.leilaodequadrinhos.api.model.entities.Bid;

import java.util.List;

public class ProgramLance {

	public static void main(String[] args) {
		
		int id_usuario = 2;
		int id_leilao = 1;
		// int id_lance = 5;
		
		
		insereLance(id_usuario, id_leilao);
		
	// carregaLance(id_lance));
		
	// carregaTodosLances();
		
	// carregaLancesPorLeilao(id_leilao);
		
	// deletarLance(23);
		
	}

	public static void deletarLance(int id_lance) {
		BidDao bidDao = DaoFactory.createLanceDao();
		bidDao.deleteLanceById(id_lance);
		
	}

	public static List<Bid> carregaLancesPorUsuario(int id_usuario) {
		BidDao bidDao = DaoFactory.createLanceDao();
		return bidDao.findByUser(ProgramUsuario.carregaUsuario(id_usuario));
	}

	public static List carregaLancesPorLeilao(int id_leilao) {
		BidDao bidDao = DaoFactory.createLanceDao();
		return bidDao.findByLeilao(ProgramLeilao.carregaLeilaoPorId(id_leilao));
	}

	public static List<Bid> carregaTodosLances() {
		BidDao bidDao = DaoFactory.createLanceDao();
		return bidDao.findAll();
	}

	public static Bid carregaLance(int id_lance) {
		BidDao bidDao = DaoFactory.createLanceDao();
		return bidDao.findById(id_lance);
	}

	public static void insereLance(int id_usuario, int id_leilao) {
		
		BidDao bidDao = DaoFactory.createLanceDao();
		bidDao.insert
		(new Bid(null,
				ProgramLeilao.carregaLeilaoPorId(id_leilao).getDefaultBid(),
				null, 
				ProgramLeilao.carregaLeilaoPorId(id_leilao), 
//				ProgramUsuario.carregaUsuario(id_usuario)));
	}
}
