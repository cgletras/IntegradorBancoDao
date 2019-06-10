package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PersonagemDao;
import model.entities.Personagem;

public class ProgramPersonagem {

	public static void main(String[] args) {
		
		
	// carregarPersonagemPorID(id_personagem);
		
	// inserirPersonagem();	
		
	// listarPersonagems();
		
	// relacionarPersonagemAProduto(id_personagem, id_produto);
		
	// listarPersonagemsPorProduto(id_produto);
			
	}

	public static List<Personagem> listarPersonagemsPorProduto(int id_produto) {
		PersonagemDao personagemDao = DaoFactory.createPersonagemDao();
		return personagemDao.findByProduto(ProgramProduto.carregarProdutoByID(id_produto));
	}

	public static Personagem carregarPersonagemPorID(int id_personagem) {
		PersonagemDao personagemDao = DaoFactory.createPersonagemDao();
		return personagemDao.findById(id_personagem);
	}

	public static void relacionarPersonagemAProduto(int id_personagem, int id_produto) {
		PersonagemDao personagemDao = DaoFactory.createPersonagemDao();
		personagemDao.relacionarPersonagemProduto(carregarPersonagemPorID(id_personagem), ProgramProduto.carregarProdutoByID(id_produto));
	}

	public static List<Personagem> listarPersonagems() {
		PersonagemDao personagemDao = DaoFactory.createPersonagemDao();
		return personagemDao.findByAll();
	}

	public static void inserirPersonagem() {
		PersonagemDao personagemDao = DaoFactory.createPersonagemDao();
		personagemDao.insertPersonagem(new Personagem(null, "Superman"));
	}

	
}
