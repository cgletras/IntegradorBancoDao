package application;

import model.dao.DaoFactory;
import model.dao.LeilaoDao;
import model.entities.Leilao;

public class ProgramLeilao {

	public static void main(String[] args) {

		int id_leilao = 1;

		carregaLeilaoPorId(id_leilao);
		
		updateLeilao(id_leilao);
		
		insereLeilao();
	}

	private static void insereLeilao() {
		// TODO Auto-generated method stub
		
	}

	private static void updateLeilao(int id_leilao) {
		// TODO Auto-generated method stub
		
	}

	public static Leilao carregaLeilaoPorId(int id_leilao) {
		LeilaoDao leilaoDao = DaoFactory.createLeilaoDao();
		return leilaoDao.findById(id_leilao);
	}
}
