package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Personagem;
import com.leilaodequadrinhos.api.model.entities.Produto;

import java.util.List;

public interface PersonagemDao extends DAO {

	List<Personagem> findAllByProduto(Produto obj);
	void relacionarPersonagemProduto(Personagem personagem, Produto produto);
}
