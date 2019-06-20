package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertProduct extends BaseProductTask implements Task {

    private static final long ACTIVE = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDao produtoDao = new ProdutoDAO();
        EstadoProdutoDao estadoProdutoDao = new EstadoProdutoDAO();
        UserDao userDao = new UserDAO();
        Produto produto = getProduct(request, userDao);
        produto.setEstado(estadoProdutoDao.findById(ACTIVE));
        produtoDao.insert(produto);
        return "Produto Inserido";
    }
}