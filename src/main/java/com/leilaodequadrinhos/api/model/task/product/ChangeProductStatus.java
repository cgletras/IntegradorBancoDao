package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeProductStatus implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProdutoDao produtoDao = new ProdutoDAO();
        EstadoProdutoDao estadoProdutoDao = new EstadoProdutoDAO();
        Integer productID = Integer.parseInt(request.getParameter("productID"));
        Long productStateID = Long.parseLong(request.getParameter("productStateID"));
        produtoDao.changeStatusProduct(productID, estadoProdutoDao.findById(productStateID));
        return "Status do produto modificado com sucesso";
    }
}