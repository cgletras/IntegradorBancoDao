package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.LanceDao;
import com.leilaodequadrinhos.api.model.entities.Lance;

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
		LanceDao lanceDao = DaoFactory.createLanceDao();
		lanceDao.deleteLanceById(id_lance);
		
	}

	public static List<Lance> carregaLancesPorUsuario(int id_usuario) {
		LanceDao lanceDao = DaoFactory.createLanceDao();
		return lanceDao.findByUser(ProgramUsuario.carregaUsuario(id_usuario));
	}

	public static List carregaLancesPorLeilao(int id_leilao) {
		LanceDao lanceDao = DaoFactory.createLanceDao();
		return lanceDao.findByLeilao(ProgramLeilao.carregaLeilaoPorId(id_leilao));
	}

	public static List<Lance> carregaTodosLances() {
		LanceDao lanceDao = DaoFactory.createLanceDao();
		return lanceDao.findAll();
	}

	public static Lance carregaLance(int id_lance) {
		LanceDao lanceDao = DaoFactory.createLanceDao();
		return lanceDao.findById(id_lance);
	}

	public static void insereLance(int id_usuario, int id_leilao) {
		
		LanceDao lanceDao = DaoFactory.createLanceDao();
		lanceDao.insert
		(new Lance(null, 
				ProgramLeilao.carregaLeilaoPorId(id_leilao).getLancePadrao(), 
				null, 
				ProgramLeilao.carregaLeilaoPorId(id_leilao), 
//				ProgramUsuario.carregaUsuario(id_usuario)));
	}
}
