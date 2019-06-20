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

public class RelateWriterToProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EscritorDao escritorDao = new EscritorDAO();
        ProdutoDao produtoDao = new ProdutoDAO();
        Long idEscritor = Long.parseLong(request.getParameter("writerID"));
        Long idProduto = Long.parseLong(request.getParameter("productID"));
        escritorDao.relacionarEscritorProduto((Escritor) escritorDao.findById(idEscritor), (Produto) produtoDao.findById(idProduto));
        return "Escritor relacionado ao quadrinho";
    }
}
