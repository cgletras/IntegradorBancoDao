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
import java.util.List;

public class FindWriterByProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("productID"));
        WriterDao writerDao = new WriterDAO();
        ProductDao productDao = new ProductDAO();
        List<Writer> writers = writerDao.findByProduct((Product) productDao.findById(id));
        request.setAttribute("writers", writers);
        return writers;
    }
}