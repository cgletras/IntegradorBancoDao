package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindProductByID implements Task {

    @Override
    public Produto execute(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDao dao = new ProdutoDAO();
        Long id = Long.parseLong(request.getParameter("productID"));
        Produto produto = (Produto) dao.findById(id);
        request.setAttribute("produto", produto);
        return produto;
    }
}