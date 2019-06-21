package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindProductByID implements Task {

    @Override
    public Product execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao dao = new ProductDAO();
        Long id = Long.parseLong(request.getParameter("productID"));
        Product product = (Product) dao.findById(id);
        request.setAttribute("product", product);
        return product;
    }
}