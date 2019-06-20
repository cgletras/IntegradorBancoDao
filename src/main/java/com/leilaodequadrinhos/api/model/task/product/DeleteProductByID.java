package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductByID implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProdutoDao produtoDao = new ProdutoDAO();
        Long productID = Long.parseLong(request.getParameter("productID"));
        produtoDao.deleteById(productID);
        return "Produto excluído";
    }
}