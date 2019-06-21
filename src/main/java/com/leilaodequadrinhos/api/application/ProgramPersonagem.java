package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.CharacterDao;
import com.leilaodequadrinhos.api.model.entities.Character;

import java.util.List;

public class ProgramPersonagem {

	public static void main(String[] args) {
		
		
	// carregarPersonagemPorID(id_personagem);
		
	// inserirPersonagem();	
		
	// listarPersonagems();
		
	// relacionarPersonagemAProduto(id_personagem, id_produto);
		
	// listarPersonagemsPorProduto(id_produto);
			
	}

	public static List<Character> listarPersonagemsPorProduto(int id_produto) {
		CharacterDao characterDao = DaoFactory.createPersonagemDao();
		return characterDao.findByProduto(ProgramProduto.carregarProdutoByID(id_produto));
	}

	public static Character carregarPersonagemPorID(int id_personagem) {
		CharacterDao characterDao = DaoFactory.createPersonagemDao();
		return characterDao.findById(id_personagem);
	}

	public static void relacionarPersonagemAProduto(int id_personagem, int id_produto) {
		CharacterDao characterDao = DaoFactory.createPersonagemDao();
		characterDao.relateCharacterToProduct(carregarPersonagemPorID(id_personagem), ProgramProduto.carregarProdutoByID(id_produto));
	}

	public static List<Character> listarPersonagems() {
		CharacterDao characterDao = DaoFactory.createPersonagemDao();
		return characterDao.findByAll();
	}

	public static void inserirPersonagem() {
		CharacterDao characterDao = DaoFactory.createPersonagemDao();
		characterDao.insertPersonagem(new Character(null, "Superman"));
	}

	
}
