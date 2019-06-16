package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.entities.Lance;
import com.leilaodequadrinhos.api.model.entities.Leilao;

import java.util.Date;
import java.util.List;

public class ProgramLeilao {

	public static void main(String[] args) {

	//	carregaLeilaoPorId(id_leilao);
		
	//	updateLeilao(id_leilao);
		
	//	insereLeilao();
		
	//	listarLeiloes();
		
	// listarLeilaoPorUsuario(id_usuario)) {
		
	//	updateLeilao(id_leilao);
		
	//	mudaStatusLeilao(id_leilao, id_estado_leilao);
		
	//	cancelarLeilao(id_leilao);
		
	}

	public static void cancelarLeilao(int id_leilao) {
		
		List<Lance> lances = ProgramLance.carregaLancesPorLeilao(id_leilao);
		System.out.println(lances.size());
		
		if(lances.size()==0) {
			mudaStatusLeilao(id_leilao, 5);
		} else {
			System.out.println("Um leil�o com lances n�o pode ser cancelado");
		}		
	}

	public static void mudaStatusLeilao(int id_leilao, int id_estado_leilao) {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		leilaoDao.changeStatusLeilao(id_leilao, ProgramEstadoLeilao.estadoLeilaoPorId(id_estado_leilao));
	}

	public static List<Leilao> listarLeilaoPorUsuario(int id_usuario) {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		return leilaoDao.findByUser(ProgramUsuario.carregaUsuario(id_usuario));
	}

	public static List<Leilao> listarLeiloes() {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		return leilaoDao.findAll();
	}

	public static void insereLeilao() {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		Leilao leilao = new Leilao();
		//carregar dados informados na pagina de atualiza�ao abaixo
		
		leilao.setDuracao(10);
		leilao.setDataInicio(new Date());
		leilao.setValorInicial(450);
		leilao.setValorAtual(leilao.getValorInicial());
		leilao.setLancePadrao(20);
		leilao.setEstado(ProgramEstadoLeilao.estadoLeilaoPorId(1));
		leilao.setProduto(ProgramProduto.carregarProdutoByID(5));
		leilao.setUser(ProgramUsuario.carregaUsuario(2));
		leilaoDao.insert(leilao);
	}

	public static void updateLeilao(int id_leilao) {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		Leilao leilao = new Leilao();
		//carregar dados informados na pagina de atualiza�ao abaixo
		
		leilao.setIdLeilao(id_leilao);
		leilao.setDuracao(20);
		leilao.setDataInicio(new Date());
		leilao.setValorInicial(200);
		leilao.setValorAtual(leilao.getValorInicial());
		leilao.setLancePadrao(30);
		leilao.setEstado(ProgramEstadoLeilao.estadoLeilaoPorId(1));
		leilao.setProduto(ProgramProduto.carregarProdutoByID(3));
		leilao.setUser(ProgramUsuario.carregaUsuario(1));
		
		List<Lance> lances = ProgramLance.carregaLancesPorLeilao(id_leilao);
		System.out.println(lances.size());
		
		if(lances.size()==0) {
			leilaoDao.update(leilao);
		} else {
			System.out.println("Um leil�o com lances n�o pode ser modificado");
		}
	}

	public static Leilao carregaLeilaoPorId(int id_leilao) {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		return leilaoDao.findById(id_leilao);
	}
}
