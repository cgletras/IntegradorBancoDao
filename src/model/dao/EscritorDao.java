package model.dao;

import java.util.List;

import model.entities.Escritor;
import model.entities.Produto;

public interface EscritorDao {

	Escritor findById(Integer id);
	List<Escritor> findByProduto(Produto obj);
	List<Escritor> findByAll();
	void insertEscritor(Escritor obj);
	void relacionarEscritorProduto(Escritor escritor, Produto produto);
}
