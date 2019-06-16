package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface LeilaoDao {

	void insert(Leilao obj);
	void update(Leilao obj);
	void updateValorAtual(Leilao obj);
	void changeStatusLeilao(Integer id, EstadoLeilao estado);
	Leilao  findById(Integer id);
	List<Leilao> findAll();
	List<Leilao> findByUser(User obj);
}
