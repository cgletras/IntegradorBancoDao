package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProduct extends BaseProductTask implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = new ProductDAO();
        ProductStatusDao productStatusDao = new ProductStatusDAO();
        UserDao userDao = new UserDAO();
        Product product = null;
        try {
            product = buildProduct(request, userDao);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            return "Product not updated";
        }
        Long statusID = Long.parseLong(request.getParameter("statusID"));
        product.setProductStatus(productStatusDao.findById(statusID));
        product.setProductID(Integer.parseInt(request.getParameter("productID")));
        productDao.update(product);
        return "Product updated";
    }
}