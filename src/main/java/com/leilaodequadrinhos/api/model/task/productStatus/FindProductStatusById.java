package com.leilaodequadrinhos.api.model.task.productStatus;

import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoProdutoDAO;
import com.leilaodequadrinhos.api.model.entities.EstadoProduto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindProductStatusById implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Long id = Long.parseLong(request.getParameter("statusID"));
        EstadoProdutoDao estadoProdutoDao = new EstadoProdutoDAO();
        EstadoProduto estadoProduto = estadoProdutoDao.findById(id);
        request.setAttribute("estado", estadoProduto);
        return estadoProduto;
    }
}
