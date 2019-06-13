package model.dao;

import java.util.List;

import model.entities.EstadoProduto;
import model.entities.Product;
import model.entities.User;

public interface ProductDao {
	
	void insertProduct(Product product);
	void updateProduct(Product product);
	Product findById(Integer id);
	List<Product> findAllByUser(User user);
	void changeStatusProduct(Integer id, EstadoProduto estado);
}
