package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.util.HashGenerator;

import javax.servlet.http.HttpServletRequest;

abstract class BaseProductTask {

    protected Product getProduct(HttpServletRequest request, UserDao userDao) {
        Product product = new Product();
        product.setPublisher(request.getParameter("publishingCompany"));
        product.setTitle(request.getParameter("title"));
        product.setComicFormat(request.getParameter("format"));
        int pages = Integer.parseInt(request.getParameter("pagesNumber"));
        product.setPagesNumber(pages);
        int weight = Integer.parseInt(request.getParameter("weight"));
        product.setWeight(weight);
        product.setCoverImage(HashGenerator.hashGenerator()+".jpeg");
        Long userID = Long.parseLong(request.getParameter("userID"));
        product.setUser((User) userDao.findById(userID));
        return product;
    }
}