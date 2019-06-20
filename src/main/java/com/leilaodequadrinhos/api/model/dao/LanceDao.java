package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Lance;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface LanceDao extends DAO {

	List<Lance> findBidByUser(Long id_user);
	List<Lance> findBidLeilao(Long id_leilao);
	Long BidCount(Long id_leilao);
}
