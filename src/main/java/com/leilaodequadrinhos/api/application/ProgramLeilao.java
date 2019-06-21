package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.Bid;

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
		
		List<Bid> bids = ProgramLance.carregaLancesPorLeilao(id_leilao);
		System.out.println(bids.size());
		
		if(bids.size()==0) {
			mudaStatusLeilao(id_leilao, 5);
		} else {
			System.out.println("Um leil�o com bids n�o pode ser cancelado");
		}		
	}

	public static void mudaStatusLeilao(int id_leilao, int id_estado_leilao) {
		AuctionDao auctionDao = DaoFactory.createLeilaoDao();
		auctionDao.changesAuctionStatus(id_leilao, ProgramEstadoLeilao.estadoLeilaoPorId(id_estado_leilao));
	}

	public static List<Auction> listarLeilaoPorUsuario(int id_usuario) {
		AuctionDao auctionDao = DaoFactory.createLeilaoDao();
		return auctionDao.findByUser(ProgramUsuario.carregaUsuario(id_usuario));
	}

	public static List<Auction> listarLeiloes() {
		AuctionDao auctionDao = DaoFactory.createLeilaoDao();
		return auctionDao.findAll();
	}

	public static void insereLeilao() {
		AuctionDao auctionDao = DaoFactory.createLeilaoDao();
		Auction auction = new Auction();
		//carregar dados informados na pagina de atualiza�ao abaixo
		
		auction.setDuration(10);
		auction.setInitialDate(new Date());
		auction.setInitialValue(450);
		auction.setCurrentValue(auction.getInitialValue());
		auction.setDefaultBid(20);
		auction.setAuctionStatus(ProgramEstadoLeilao.estadoLeilaoPorId(1));
		auction.setProduct(ProgramProduto.carregarProdutoByID(5));
		auction.setUser(ProgramUsuario.carregaUsuario(2));
		auctionDao.insert(auction);
	}

	public static void updateLeilao(int id_leilao) {
		AuctionDao auctionDao = DaoFactory.createLeilaoDao();
		Auction auction = new Auction();
		//carregar dados informados na pagina de atualiza�ao abaixo
		
		auction.setAuctionID(id_leilao);
		auction.setDuration(20);
		auction.setInitialDate(new Date());
		auction.setInitialValue(200);
		auction.setCurrentValue(auction.getInitialValue());
		auction.setDefaultBid(30);
		auction.setAuctionStatus(ProgramEstadoLeilao.estadoLeilaoPorId(1));
		auction.setProduct(ProgramProduto.carregarProdutoByID(3));
		auction.setUser(ProgramUsuario.carregaUsuario(1));
		
		List<Bid> bids = ProgramLance.carregaLancesPorLeilao(id_leilao);
		System.out.println(bids.size());
		
		if(bids.size()==0) {
			auctionDao.update(auction);
		} else {
			System.out.println("Um leil�o com bids n�o pode ser modificado");
		}
	}

	public static Auction carregaLeilaoPorId(int id_leilao) {
		AuctionDao auctionDao = DaoFactory.createLeilaoDao();
		return auctionDao.findById(id_leilao);
	}
}
