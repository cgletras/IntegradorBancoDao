package model.dao;

import java.util.List;

import model.entities.User;

public interface UserDao {

	void insert(User obj);
	void update(User obj);
	void inactivate(Integer id);
	void activate(Integer id);
	User findById(Integer id);
	User findByEmail(String email);
	List<User> findAll();
}
