package com.leilaodequadrinhos.api.model.task.writer;

import com.leilaodequadrinhos.api.model.dao.EscritorDao;
import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EscritorDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.entities.Escritor;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindWriterByProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("productID"));
        EscritorDao escritorDao = new EscritorDAO();
        ProdutoDao produtoDao = new ProdutoDAO();
        List<Escritor> list = escritorDao.findByProduto((Produto) produtoDao.findById(id));
        request.setAttribute("escritores", list);
        return list;
    }
}