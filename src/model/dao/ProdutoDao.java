package model.dao;

import java.util.List;

import model.entities.Produto;
import model.entities.Usuario;

public interface ProdutoDao {
	
	void insertProduct(Produto produto);
	Produto findById(Integer id);
	List<Produto> findAllByUser(Usuario usuario);
}
