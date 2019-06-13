package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class ProgramProduto {

	public static void main(String[] args) {

		int id_produto = 11;
		int id_usuario = 1;
		int id_estado_produto = 1;
		
	//	carregarProdutoByID(id_produto);
		
	// carregarProdutosPorUsuario(id_usuario);
			
	//	inserirProduto(id_usuario);
		
	//	atualizarProduto(id_produto, id_usuario);
		
		//	mudaStatusProduto(id_produto, id_estado_produto);
	}

	public static void atualizarProduto(int id_produto, int id_usuario) {
		ProductDao productDao = DaoFactory.createProdutoDao();
		Product product = new Product();
		product.setEditora("Marvel");
		product.setTitulo("Greatest Vilains of the Fantastic Four");
		product.setFormatoDoQuadrinho("TPB");
		product.setNumeroPaginas(90);
		product.setPeso(150);
		product.setCapaImagem("capaImagem");
		product.setEstado(ProgramEstadoProduto.estadoProdutoPorId(1));
		product.setUser(ProgramUsuario.carregaUsuario(id_usuario));
		product.setIdProduto(id_produto);
		
		productDao.updateProduct(product);
		
	}

	public static void mudaStatusProduto(int id_produto, int id_estado_produto) {
		ProductDao productDao = DaoFactory.createProdutoDao();
		productDao.changeStatusProduct(id_produto, ProgramEstadoProduto.estadoProdutoPorId(id_estado_produto));
	}

	public static void inserirProduto(int id_usuario) {
		ProductDao productDao = DaoFactory.createProdutoDao();
		Product product = new Product();
		product.setEditora("Marvel");
		product.setTitulo("Greatest Vilains of the Fantastic Four");
		product.setFormatoDoQuadrinho("TPB");
		product.setNumeroPaginas(85);
		product.setPeso(150);
		product.setCapaImagem("capaImagem");
		product.setEstado(ProgramEstadoProduto.estadoProdutoPorId(1));
		product.setUser(ProgramUsuario.carregaUsuario(id_usuario));
		
		productDao.insertProduct(product);
	}

	public static List<Product> carregarProdutosPorUsuario(int id_usuario) {
		ProductDao productDao = DaoFactory.createProdutoDao();
		return productDao.findAllByUser(ProgramUsuario.carregaUsuario(id_usuario));
	}

	public static Product carregarProdutoByID(int id_produto) {
		ProductDao productDao = DaoFactory.createProdutoDao();
		return productDao.findById(id_produto);
	}

}
