package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Personagem;
import com.leilaodequadrinhos.api.model.entities.Produto;

import java.util.List;

public interface PersonagemDao {
	
	Personagem findById(Integer id);
	List<Personagem> findByProduto(Produto obj);
	List<Personagem> findByAll();
	void insertPersonagem(Personagem obj);
	void relacionarPersonagemProduto(Personagem personagem, Produto produto);
}
