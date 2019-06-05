package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.LanceDao;
import model.entities.Lance;
import model.entities.Leilao;
import model.entities.Usuario;
import application.*;
import db.DB;
import db.DbIntegrityException;

public class ProgramLance {

	
	public static void main(String[] args) {
		
		int id_usuario = 5;
		int id_leilao = 1;
		int id_lance = 5;
		
		
		insereLance(id_usuario, id_leilao);
		
	// carregaLance(id_lance));
		
	//  carregaTodosLances();
		
	//	carregaLancesPorLeilao(id_leilao);
		
	
	
		
		 deletarLance(14);
		
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
				new Date(), 
				ProgramLeilao.carregaLeilaoPorId(id_leilao), 
				ProgramUsuario.carregaUsuario(id_usuario)));		
	}
}
