package model.dao;

import java.util.List;

import model.entities.Personagem;
import model.entities.Produto;

public interface PersonagemDao {
	
	Personagem findById(Integer id);
	List<Personagem> findByProduto(Produto obj);
	List<Personagem> findByAll();
	void insertPersonagem(Personagem obj);
	void relacionarPersonagemProduto(Personagem personagem, Produto produto);
}
