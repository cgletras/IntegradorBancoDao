package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;
import com.leilaodequadrinhos.api.model.task.Task;
import com.leilaodequadrinhos.api.model.task.product.ChangeProductStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAuctionByID implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        AuctionDao auctionDao = new AuctionDAO();
        ProductDao productDao = new ProductDAO();

        Auction auction = (Auction) auctionDao.findById(auctionID);
        Product product = auction.getProduct();
        ProductStatus productStatus = product.getProductStatus();
        productStatus.setProductStatusID(1);
        productDao.changeStatusProduct((long) product.getProductID(), productStatus);

        auctionDao.deleteById(auctionID);
        // se quiser passar algum parametro pra dentro
        request.setAttribute("xxx", " yyy");

        // aqui vc chama....
        String statusOfProduct = new ChangeProductStatus().execute(request, response);;

        return "This auction has been deleted";
    }
}