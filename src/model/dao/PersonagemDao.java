package model.dao;

import java.util.List;

import model.entities.Personagem;
import model.entities.Product;

public interface PersonagemDao {
	
	Personagem findById(Integer id);
	List<Personagem> findByProduto(Product obj);
	List<Personagem> findByAll();
	void insertPersonagem(Personagem obj);
	void relacionarPersonagemProduto(Personagem personagem, Product product);
}
