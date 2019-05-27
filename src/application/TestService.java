package application;

import java.util.Date;

import model.dao.LanceDao;
import model.entities.Lance;
import model.entities.Leilao;
import model.entities.Usuario;

public class TestService {

	LanceDao lanceDao;
	
	public TestService(LanceDao lanceDao) {
		this.lanceDao = lanceDao;
	}
	
	public void TestInsert() {
		Double valorLance = 20.0;
		Date dataLance = new Date();
		
		Leilao leilao = new Leilao();
		leilao.setIdLeilao(1);
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1);
		
		lanceDao.insert(new Lance(null, valorLance, dataLance, leilao, usuario));
	}
}
