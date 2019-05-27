package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.EstadoLeilaoDao;
import model.dao.UsuarioDao;
import model.entities.EstadoLeilao;
import model.entities.Usuario;

public class Program {

	public static void main(String[] args) {
		;
		//Find EstadoLeilao by id
		int id = 3;
		EstadoLeilaoDao escritorDao = DaoFactory.createEstadoLeilaoDao();
		EstadoLeilao escritor = escritorDao.findById(id);
		System.out.println(escritor);
		
//ERRo  List<EstadoLeilao> list = EstadoLeilaoDao.findAll();
		//for (EstadoLeilao obj : list) {
			//System.out.println(obj);}
		
		//INSERIR USUARIO
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		usuarioDao.insert(new Usuario(null, "Greg", "grg@ail", "gyfupq", new Date(), true));
		System.out.println("Inserted new Id= ");
		
		// UPDATE USUARIO
		//UsuarioDao//
//ERRo		int ido = 1;
			usuarioDao = DaoFactory.createUsuarioDao();
			Usuario usuario = usuarioDao.findById(ido);
			usuarioDao.update(usuario);
		
	}
}
