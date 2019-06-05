package application;

import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.entities.Produto;

public class ProgramProduto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int id_produto = 4;
		Produto p  = carregarProdutoByID(id_produto);
		System.out.println(p);
		
	}

	public static Produto carregarProdutoByID(int id) {
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		return produtoDao.findById(id);
		
	}

}
