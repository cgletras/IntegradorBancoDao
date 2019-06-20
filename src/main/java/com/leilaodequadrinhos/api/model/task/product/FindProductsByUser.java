package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindProductsByUser implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDao produtoDao = new ProdutoDAO();
        Long userID = Long.parseLong(request.getParameter("userID"));
        List<Produto> produtos = produtoDao.findAllByUser(userID);
        request.setAttribute("produtos", produtos);
        return produtos;
    }
}