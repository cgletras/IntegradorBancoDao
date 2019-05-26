package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.EscritorDao;
import model.entities.Escritor;
import model.entities.Usuario;

public class Program {

	public static void main(String[] args) {
		
		EscritorDao escritorDao = DaoFactory.createEscritorDao();
		Escritor newEscritor = new Escritor(null, "Greg");
		escritorDao.insert(newEscritor);
		
		
	}

}
