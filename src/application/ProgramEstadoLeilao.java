package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.EstadoLeilaoDao;
import model.dao.UsuarioDao;
import model.entities.EstadoLeilao;
import model.entities.Usuario;

public class ProgramEstadoLeilao {

	public static void main(String[] args) {
		
		EstadoLeilaoDao escritorDao = DaoFactory.createEstadoLeilaoDao();
		
		//Find EstadoLeilao by id
		int id = 3;
		EstadoLeilao escritor = escritorDao.findById(id);
		System.out.println(escritor);
		
// ERRO Lista todos os leiloes.
		
/*		List<EstadoLeilao> list = EstadoLeilaoDao.findAll();
		for (EstadoLeilao obj : list) {
		System.out.println(obj);}
*/		
	}
}
