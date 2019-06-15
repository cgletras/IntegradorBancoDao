package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.entities.Produto;

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
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        Produto produto = new Produto();
        produto.setEditora("Marvel");
        produto.setTitulo("Greatest Vilains of the Fantastic Four");
        produto.setFormatoDoQuadrinho("TPB");
        produto.setNumeroPaginas(90);
        produto.setPeso(150);
        produto.setCapaImagem("capaImagem");
        produto.setEstado(ProgramEstadoProduto.estadoProdutoPorId(1));
        produto.setUser(ProgramUsuario.carregaUsuario(id_usuario));
        produto.setIdProduto(id_produto);

        produtoDao.updateProduct(produto);
    }

    public static void mudaStatusProduto(int id_produto, int id_estado_produto) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        produtoDao.changeStatusProduct(id_produto, ProgramEstadoProduto.estadoProdutoPorId(id_estado_produto));
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
        produto.setEstado(ProgramEstadoProduto.estadoProdutoPorId(1));
        produto.setUser(ProgramUsuario.carregaUsuario(id_usuario));

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
