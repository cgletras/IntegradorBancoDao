package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.LeilaoDao;
import model.entities.EstadoLeilao;
import model.entities.Leilao;

public class ProgramLeilao {

	public static void main(String[] args) {

		int id_leilao = 1;

	//	carregaLeilaoPorId(id_leilao);
		
	//	updateLeilao(id_leilao);
		
	//	insereLeilao();
		
	//	listarLeiloes();
			
	}

	public static List<Leilao> listarLeiloes() {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		return leilaoDao.findAll();
	}

	public static void insereLeilao() {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		Leilao leilao = new Leilao();
		leilao.setDuracao(10);
		leilao.setDataInicio(new Date());
		leilao.setValorInicial(450);
		leilao.setValorAtual(leilao.getValorInicial());
		leilao.setLancePadrao(20);
		leilao.setEstado(ProgramEstadoLeilao.estadoLeilaoPorId(1));
		leilao.setProduto(ProgramProduto.carregarProdutoByID(4));
		leilao.setUsuario(ProgramUsuario.carregaUsuario(1));
		leilaoDao.insert(leilao);
	}

	public static void updateLeilao(int id_leilao) {
		// TODO Auto-generated method stub
		
	}

	public static Leilao carregaLeilaoPorId(int id_leilao) {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		return leilaoDao.findById(id_leilao);
	}
}
