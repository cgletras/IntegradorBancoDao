package com.leilaodequadrinhos.api.model.task.estadoProduto;

import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoProdutoDAO;
import com.leilaodequadrinhos.api.model.entities.EstadoProduto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListAllProductStatus implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EstadoProdutoDao estadoProdutoDao = new EstadoProdutoDAO();
        List<EstadoProduto> list = estadoProdutoDao.findAll();
        request.setAttribute("estados-produtos", list);
        return list;
    }
}
