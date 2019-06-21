package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCurrentAuctionPrice implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuctionDao auctionDao = new AuctionDAO();
        Auction auction = new Auction();
        auction.setAuctionID(Long.parseLong(request.getParameter("auctionID")));
        auction.setDefaultBid(Float.parseFloat(request.getParameter("baseBid")));
        auctionDao.updateInitialValue(auction);
        return "The current value of the auction has been updated";
    }
}