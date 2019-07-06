package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.util.HashGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

abstract class BaseProductTask {

    protected Product buildProduct(HttpServletRequest request, UserDao userDao) throws IOException, ServletException {
        Product product = new Product();
        product.setPublisher(request.getParameter("publishingCompany"));
        product.setTitle(request.getParameter("title"));
        product.setComicFormat(request.getParameter("format"));
        int pages = Integer.parseInt(request.getParameter("pagesNumber"));
        product.setPagesNumber(pages);
        int weight = Integer.parseInt(request.getParameter("weight"));
        product.setWeight(weight);

        if (request.getParameter("updateImage") != null && (Boolean.parseBoolean(request.getParameter("updateImage")))) {
            product.setCoverImage(new ReceiveProductImage().execute(request));
        } else {
            product.setCoverImage(
                new ProductDAO().findById(Long.valueOf(request.getParameter("productID"))).getCoverImage()
            );
        }

        Long userID = Long.valueOf((((User) request.getSession().getAttribute("user")).getUserID()));
        product.setUser((User) userDao.findById(userID));
        return product;
    }
}