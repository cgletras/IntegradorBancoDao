package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import model.dao.DaoFactory;
import model.dao.EstadoLeilaoDao;
import model.dao.UsuarioDao;
import model.entities.EstadoLeilao;
import model.entities.Usuario;

public class ProgramEstadoLeilao {

	public static void main(String[] args) {
		
		
		
		//Find EstadoLeilao by id
		int id = 1;
		
//OK	estadoLeilaoPorId(id);
		
		//Lista todos os EstadosLeiloes.
		
//OK	listarEstadosLeilao();
		
				
	}

	private static List<EstadoLeilao> listarEstadosLeilao() {
		EstadoLeilaoDao estadoLeilaoDao = DaoFactory.createEstadoLeilaoDao();
		return estadoLeilaoDao.findAll();
	}

	private static EstadoLeilao estadoLeilaoPorId(int id) {
		EstadoLeilaoDao estadoLeilaoDao = DaoFactory.createEstadoLeilaoDao();
		return estadoLeilaoDao.findById(id);
	}
}
