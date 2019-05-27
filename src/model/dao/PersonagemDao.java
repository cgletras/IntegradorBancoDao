package model.dao;

import java.util.List;

import model.entities.Personagem;
import model.entities.Produto;

public interface PersonagemDao {
	
	Personagem findById(Integer id);
	List<Personagem> findByProduto(Produto obj);
}
