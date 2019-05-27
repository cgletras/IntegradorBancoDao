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
		
		int id = 3;
		
		Leilao leilao = new Leilao();
		
		leilao.setIdLeilao(1);
		
		LanceDao lanceDao = DaoFactory.createLanceDao();
		lanceDao.insert(new Lance(null, 30, new Date(), leilao, ProgramUsuario.carregaUsuario(id)));
	}
}
