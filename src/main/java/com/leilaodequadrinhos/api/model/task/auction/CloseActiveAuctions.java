package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CloseActiveAuctions {

    private static final int CLOSED = 4;

    public void execute() {
        AuctionDao auctionDao = new AuctionDAO();
        ProductDao productDao = new ProductDAO();
        ProductStatus productStatus = new ProductStatus();
        productStatus.setProductStatusID(CLOSED);
        List<Auction> auctions = auctionDao.findAllActiveAuctions();

        for (Auction auction : auctions) {
            if (auction.getDuration() == 0) {
                auctionDao.closeActiveAuction(auction.getAuctionID());
                productDao.changeStatusProduct((long) auction.getProduct().getProductID(), productStatus);
            }
        }
    }
}