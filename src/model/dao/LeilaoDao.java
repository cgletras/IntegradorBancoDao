package model.dao;

import java.util.List;

import model.entities.EstadoLeilao;
import model.entities.Leilao;
import model.entities.Usuario;

public interface LeilaoDao {

	void insert(Leilao obj);
	void update(Leilao obj);
	void updateValorAtual(Leilao obj);
	void changeStatusLeilao(Integer id, EstadoLeilao estado);
	Leilao  findById(Integer id);
	List<Leilao> findAll();
	List<Leilao> findByUser(Usuario obj);
}
