package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.User;

public interface UserDao extends DAO {

	void activate(Integer id);
	Long count();
	User findByEmail(String email);
	Boolean hasActiveAuction(Long id);
}