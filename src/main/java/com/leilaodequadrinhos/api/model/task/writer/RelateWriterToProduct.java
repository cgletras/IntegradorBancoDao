package com.leilaodequadrinhos.api.model.task.writer;

import com.leilaodequadrinhos.api.model.dao.WriterDao;
import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.WriterDAO;
import com.leilaodequadrinhos.api.model.entities.Writer;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RelateWriterToProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        WriterDao writerDao = new WriterDAO();
        ProductDao productDao = new ProductDAO();
        Long writerID = Long.parseLong(request.getParameter("writerID"));
        Long productID = Long.parseLong(request.getParameter("productID"));
        writerDao.relateWriterToProduct((Writer) writerDao.findById(writerID), (Product) productDao.findById(productID));
        return "Writer related to the comic";
    }
}