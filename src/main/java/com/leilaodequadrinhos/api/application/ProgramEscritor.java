package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.EscritorDao;
import com.leilaodequadrinhos.api.model.entities.Escritor;

import java.util.List;

public class ProgramEscritor {

	public static void main(String[] args) {
		
		
		//	carregarEscritorPorID(2);
		
	//	inserirEscritor();	
		
	// listarEscritores();
		
		//	relacionarEscritorAProduto(4, 11);
		
		// listarEscritoresPorProduto(id_produto)) {
			
	}

	public static List<Escritor> listarEscritoresPorProduto(int id_produto) {
		EscritorDao escritorDao = DaoFactory.createEscritorDao();
		return escritorDao.findByProduto(ProgramProduto.carregarProdutoByID(id_produto));
	}

	public static Escritor carregarEscritorPorID(int id_escritor) {
		EscritorDao escritorDao = DaoFactory.createEscritorDao();
		return escritorDao.findById(id_escritor);
	}

	public static void relacionarEscritorAProduto(int id_escritor, int id_produto) {
		EscritorDao escritorDao = DaoFactory.createEscritorDao();
		escritorDao.relacionarEscritorProduto(carregarEscritorPorID(id_escritor), ProgramProduto.carregarProdutoByID(id_produto));
	}

	public static List<Escritor> listarEscritores() {
		EscritorDao escritorDao = DaoFactory.createEscritorDao();
		return escritorDao.findByAll();
	}

	public static void inserirEscritor() {
		EscritorDao escritorDao = DaoFactory.createEscritorDao();
		escritorDao.insertEscritor(new Escritor(null, "Neil Gaiman"));
	}

	
}
