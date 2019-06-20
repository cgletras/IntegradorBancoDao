package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllProducts implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProdutoDao produtoDao = new ProdutoDAO();
        List<Produto> produtos = produtoDao.findAll();
        request.setAttribute("produtos", produtos);
        return produtos;
    }
}