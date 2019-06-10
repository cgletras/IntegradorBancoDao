package model.dao;

import java.util.List;

import model.entities.Lance;
import model.entities.Leilao;
import model.entities.User;

public interface LanceDao {

	void insert(Lance obj);
	Lance findById(Integer id);
	List<Lance> findAll();
	List<Lance> findByUser(User obj);
	List<Lance> findByLeilao(Leilao obj);
	void deleteLanceById(Integer id_lance);
}
