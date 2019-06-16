package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Lance;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface LanceDao {

	void insert(Lance obj);
	Lance findById(Integer id);
	List<Lance> findAll();
	List<Lance> findByUser(User obj);
	List<Lance> findByLeilao(Leilao obj);
	void deleteLanceById(Integer id_lance);
}
