package com.leilaodequadrinhos.api.model.task.productStatus;

import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindProductStatusById implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Long productStatusID = Long.parseLong(request.getParameter("productStatusID"));
        ProductStatusDao productStatusDao = new ProductStatusDAO();
        ProductStatus productStatus = productStatusDao.findById(productStatusID);
        request.setAttribute("productStatus", productStatus);
        return productStatus;
    }
}