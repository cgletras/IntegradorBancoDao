package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.AuctionStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionStatusDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.entities.AuctionStatus;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeAuctionStatus implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Integer auctionID = Integer.parseInt(request.getParameter("auctionID"));
        Long auctionStatusID = Long.parseLong(request.getParameter("auctionStatusID"));
        AuctionDao auctionDao = new AuctionDAO();
        AuctionStatusDao auctionStatusDao = new AuctionStatusDAO();
        AuctionStatus auctionStatus = auctionStatusDao.findById(auctionStatusID);
        auctionDao.changesAuctionStatus(auctionID, auctionStatus);
        return "Auction status changed";
    }
}