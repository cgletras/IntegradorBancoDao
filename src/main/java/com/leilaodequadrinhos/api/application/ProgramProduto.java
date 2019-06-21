package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.entities.Product;

import java.util.List;

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
        product.setPublisher("Marvel");
        product.setTitle("Greatest Vilains of the Fantastic Four");
        product.setComicFormat("TPB");
        product.setPagesNumber(90);
        product.setWeight(150);
        product.setCoverImage("capaImagem");
        product.setProductStatus(ProgramEstadoProduto.estadoProdutoPorId(1));
        product.setUser(ProgramUsuario.carregaUsuario(id_usuario));
        product.setProductID(id_produto);

        productDao.updateProduct(product);
    }

    public static void mudaStatusProduto(int id_produto, int id_estado_produto) {
        ProductDao productDao = DaoFactory.createProdutoDao();
        productDao.changeStatusProduct(id_produto, ProgramEstadoProduto.estadoProdutoPorId(id_estado_produto));
    }

    public static void inserirProduto(int id_usuario) {
        ProductDao productDao = DaoFactory.createProdutoDao();
        Product product = new Product();
        product.setPublisher("Marvel");
        product.setTitle("Greatest Vilains of the Fantastic Four");
        product.setComicFormat("TPB");
        product.setPagesNumber(85);
        product.setWeight(150);
        product.setCoverImage("capaImagem");
        product.setProductStatus(ProgramEstadoProduto.estadoProdutoPorId(1));
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
