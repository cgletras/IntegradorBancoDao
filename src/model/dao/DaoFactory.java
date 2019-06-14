package model.dao;

import db.DB;
import model.dao.impl.EscritorDaoJDBC;
import model.dao.impl.EstadoLeilaoDaoJDBC;
import model.dao.impl.EstadoProdutoDaoJDBC;
import model.dao.impl.LanceDaoJDBC;
import model.dao.impl.LeilaoDaoJDBC;
import model.dao.impl.PersonagemDaoJDBC;
import model.dao.impl.ProductDaoJDBC;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

	public static EscritorDao createEscritorDao() {
		return new EscritorDaoJDBC(DB.getConnection());
	}
	
	public static EstadoLeilaoDao createEstadoLeilaoDao() {
		return new EstadoLeilaoDaoJDBC(DB.getConnection());
	}
	
	public static EstadoProdutoDao createEstadoProdutoDao() {
		return new EstadoProdutoDaoJDBC(DB.getConnection());
	}
	
	public static LanceDao createLanceDao() {
		return new LanceDaoJDBC(DB.getConnection());
	}
	
	public static LeilaoDao createLeilaoDao() {
		return new LeilaoDaoJDBC(DB.getConnection());
	}
	
	public static PersonagemDao createPersonagemDao() {
		return new PersonagemDaoJDBC(DB.getConnection());
	}
	
	public static ProductDao createProdutoDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static UserDao createUsuarioDao() {
		return new UserDaoJDBC(DB.getConnection());
	}
	
}
