package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.entities.EstadoProduto;
import model.entities.Produto;
import model.entities.Usuario;

public class ProgramProduto {

	public static void main(String[] args) {

		int id_produto = 4;
		int id_usuario = 1;
		
	//	carregarProdutoByID(id_produto);
		
	// carregarProdutosPorUsuario(id_usuario);
			
	//	inserirProduto(id_usuario);
	}

	public static void inserirProduto(int id_usuario) {
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		Produto produto = new Produto();
		produto.setEditora("Marvel");
		produto.setTitulo("Greatest Vilains of the Fantastic Four");
		produto.setFormatoDoQuadrinho("TPB");
		produto.setNumeroPaginas(85);
		produto.setPeso(150);
		produto.setCapaImagem("capaImagem");
		
		EstadoProduto estado = new EstadoProduto();
		estado.setIdEstadoProduto(1);
		produto.setEstado(estado);
		
		produto.setUsuario(ProgramUsuario.carregaUsuario(id_usuario));
		produtoDao.insertProduct(produto);
	}

	public static List<Produto> carregarProdutosPorUsuario(int id_usuario) {
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		return produtoDao.findAllByUser(ProgramUsuario.carregaUsuario(id_usuario));
	}

	public static Produto carregarProdutoByID(int id_produto) {
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		return produtoDao.findById(id_produto);
	}

}
