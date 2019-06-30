package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeProductStatus implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = new ProductDAO();
        ProductStatusDao productStatusDao = new ProductStatusDAO();
        Long productID = Long.parseLong(request.getParameter("productID"));
        Long productStateID = Long.parseLong(request.getParameter("productStateID"));
        Product product = (Product) productDao.findById(productID);

        boolean changeStateToActive = productStateID == 1;
        boolean changeStateToInactive = productStateID == 2;
        boolean changeStateToInAuction = productStateID == 3;
        boolean changeStateToAuctioned = productStateID == 4;

        switch (product.getProductStatus().getStatus()){
            case "ATIVO": if(changeStateToInactive ||changeStateToInAuction){
                productDao.changeStatusProduct(productID, productStatusDao.findById(productStateID));
                return "Successfully modified product status to "+productStatusDao.findById(productStateID).getStatus();
            } else {
                return "Cannot modify product status";
            }
            case "INATIVO": if(changeStateToActive) {
                productDao.changeStatusProduct(productID, productStatusDao.findById(productStateID));
                return "Successfully modified product status to "+productStatusDao.findById(productStateID).getStatus();
            } else {
                return "Cannot modify product status";
            }
            case "EM_LEILAO": if(changeStateToAuctioned){
                productDao.changeStatusProduct(productID, productStatusDao.findById(productStateID));
                return "Successfully modified product status to "+productStatusDao.findById(productStateID).getStatus();
            } else if (changeStateToActive) {
                productDao.changeStatusProduct(productID, productStatusDao.findById(productStateID));
                return "Successfully modified product status to "+productStatusDao.findById(productStateID).getStatus();
            } else {
                return "Cannot modify product status";
            }
        }
        return "Successfully modified product status";
    }
}