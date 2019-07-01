package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.BidDao;
import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.BidDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAuctionByID implements Task {

    private static final long ACTIVE = 1;

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        AuctionDao auctionDao = new AuctionDAO();
        ProductDao productDao = new ProductDAO();
        BidDao bidDao = new BidDAO();
        Long count = bidDao.BidCount(auctionID);

        if (count ==0){
            Auction auction = (Auction) auctionDao.findById(auctionID);
            Product product = auction.getProduct();
            ProductStatus productStatus = product.getProductStatus();
            productStatus.setProductStatusID((int) ACTIVE);
            productDao.changeStatusProduct((long) product.getProductID(), productStatus);
            auctionDao.deleteById(auctionID);
            return "This auction has been deleted";
        } else{
            return "You cannot delete an auction with bids";
        }
        // se quiser passar algum parametro pra dentro
//        request.setAttribute("xxx", " yyy");
//
//        // aqui vc chama....
//        String statusOfProduct = new ChangeProductStatus().execute(request, response);
    }
}