package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.AuctionStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionStatusDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateAuction extends BaseAuctionTask implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuctionDao auctionDao = new AuctionDAO();
        AuctionStatusDao auctionStatusDao = new AuctionStatusDAO();
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDao = new UserDAO();
        Auction auction = getAuction(request, productDAO, userDao);
        Long auctionStatusID = Long.parseLong(request.getParameter("auctionStatusID"));
        auction.setAuctionStatus(auctionStatusDao.findById(auctionStatusID));
        auction.setAuctionID(Long.parseLong(request.getParameter("auctionID")));
        auctionDao.update(auction);
        return "Successfully updated auction";
    }
}