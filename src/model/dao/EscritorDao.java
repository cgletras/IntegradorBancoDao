package model.dao;

import java.util.List;

import model.entities.Escritor;
import model.entities.Product;

public interface EscritorDao {

	Escritor findById(Integer id);
	List<Escritor> findByProduto(Product obj);
	List<Escritor> findByAll();
	void insertEscritor(Escritor obj);
	void relacionarEscritorProduto(Escritor escritor, Product product);
}
