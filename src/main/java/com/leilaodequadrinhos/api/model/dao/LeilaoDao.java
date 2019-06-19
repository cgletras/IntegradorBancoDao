package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface LeilaoDao extends DAO {

	void updateValorAtual(Leilao obj);
	void changeStatusLeilao(Integer id, EstadoLeilao estado);
	List<Leilao> findByUser(User obj);
}
