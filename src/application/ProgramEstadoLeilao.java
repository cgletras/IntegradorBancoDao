package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EstadoLeilaoDao;
import model.entities.EstadoLeilao;

public class ProgramEstadoLeilao {

	public static void main(String[] args) {
		
		
		
		//Find EstadoLeilao by id
		int id = 1;
		
//OK	estadoLeilaoPorId(id);
		
		//Lista todos os EstadosLeiloes.
		
//OK	listarEstadosLeilao();
		
				
	}

	public static List<EstadoLeilao> listarEstadosLeilao() {
		EstadoLeilaoDao estadoLeilaoDao = DaoFactory.createEstadoLeilaoDao();
		return estadoLeilaoDao.findAll();
	}

	public static EstadoLeilao estadoLeilaoPorId(int id) {
		EstadoLeilaoDao estadoLeilaoDao = DaoFactory.createEstadoLeilaoDao();
		return estadoLeilaoDao.findById(id);
	}
}
