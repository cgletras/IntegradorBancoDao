package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.LanceDao;
import model.entities.Lance;
import model.entities.Leilao;
import model.entities.Usuario;
import application.*;

public class ProgramLance {

	public static void main(String[] args) {
		
		int id_usuario = 1;
		int id_leilao = 1;
		int id_lance = 1;
		
	//	insereLance(id_usuario, id_leilao);
		
	//	carregaLance(id_lance);
		
	//  carregaTodosLances();
		
	//	carregaLancesPorLeilao(id_leilao);
		
	// carregaLancesPorLeilao(id_leilao);
		
		
	}

	private static List carregaLancesPorLeilao(int id_leilao) {
		LanceDao lanceDao = DaoFactory.createLanceDao();
		return lanceDao.findByLeilao(ProgramLeilao.carregaLeilaoPorId(id_leilao));
	}

	private static List<Lance> carregaTodosLances() {
		LanceDao lanceDao = DaoFactory.createLanceDao();
		return lanceDao.findAll();
	}

	private static Lance carregaLance(int id_lance) {
		LanceDao lanceDao = DaoFactory.createLanceDao();
		return lanceDao.findById(id_lance);
	}

	private static void insereLance(int id_usuario, int id_leilao) {
		
		LanceDao lanceDao = DaoFactory.createLanceDao();
		lanceDao.insert
		(new Lance(null, 
				ProgramLeilao.carregaLeilaoPorId(id_leilao).getLancePadrao(), 
				new Date(), 
				ProgramLeilao.carregaLeilaoPorId(id_leilao), 
				ProgramUsuario.carregaUsuario(id_usuario)));		
	}
}
