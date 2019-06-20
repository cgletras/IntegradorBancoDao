package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProdutoDao produtoDao = new ProdutoDAO();
        Long productCount = produtoDao.count();
        request.setAttribute("QuantidadeProdutos", productCount);
        return productCount;
    }
}