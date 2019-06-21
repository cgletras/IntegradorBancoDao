package com.leilaodequadrinhos.api.model.task.productStatus;

import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllProductStatus implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductStatusDao productStatusDao = new ProductStatusDAO();
        List<ProductStatus> listProductStatus = productStatusDao.findAll();
        request.setAttribute("productStatus", listProductStatus);
        return listProductStatus;
    }
}