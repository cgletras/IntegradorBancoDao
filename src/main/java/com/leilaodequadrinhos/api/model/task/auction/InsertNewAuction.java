package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionStatusDao;
import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.*;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertNewAuction extends BaseAuctionTask implements Task {

    private static final long ACTIVE = 1;

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuctionDao auctionDao = new AuctionDAO();
        AuctionStatusDao auctionStatusDao = new AuctionStatusDAO();
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDao = new UserDAO();
        Auction auction = buildAuction(request, productDAO, userDao);
        auction.setAuctionStatus(auctionStatusDao.findById(ACTIVE));
        auctionDao.insert(auction);
        return "Registered auction";
    }
}