package model.dao;

import java.util.List;

import model.entities.Leilao;
import model.entities.Usuario;

public interface UsuarioDao {

	void insert(Usuario obj);
	void update(Usuario obj);
	void inactivate(Integer id);
	Usuario findById(Integer id);
	Usuario findByEmail(String email);
	List<Usuario> findAll();
	List<Usuario> findByLeilao(Leilao obj);
}
