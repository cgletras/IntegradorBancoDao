package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindProductsByUser implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = new ProductDAO();
    Long userID = Long.valueOf((((User) request.getSession().getAttribute("user")).getUserID()));
        List<Product> products = productDao.findAllByUser(userID);
        request.setAttribute("products", products);
        return products;
    }
}