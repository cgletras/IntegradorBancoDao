package application;

import java.util.Date;

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
		
		insereLance(id_usuario, id_leilao);
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
