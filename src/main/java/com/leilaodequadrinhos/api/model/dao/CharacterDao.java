package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Character;
import com.leilaodequadrinhos.api.model.entities.Product;

import java.util.List;

public interface CharacterDao extends DAO {

	List<Character> findAllByProduct(Product obj);
	void relateCharacterToProduct(Character character, Product product);
}