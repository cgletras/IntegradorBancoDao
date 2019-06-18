package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface UserDao extends DAO {

	void activate(Integer id);
	User findByEmail(String email);
}
