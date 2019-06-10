package model.dao;

import java.util.List;

import model.entities.EstadoProduto;
import model.entities.Produto;
import model.entities.Usuario;

public interface ProdutoDao {
	
	void insertProduct(Produto produto);
	void updateProduct(Produto produto);
	Produto findById(Integer id);
	List<Produto> findAllByUser(Usuario usuario);
	void changeStatusProduct(Integer id, EstadoProduto estado);
}
