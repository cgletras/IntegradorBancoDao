package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface UserDao {

	void insert(User obj);
	void update(User obj);
	void inactivate(Integer id);
	void activate(Integer id);
	User findById(Integer id);
	User findByEmail(String email);
	List<User> findAll();
}
