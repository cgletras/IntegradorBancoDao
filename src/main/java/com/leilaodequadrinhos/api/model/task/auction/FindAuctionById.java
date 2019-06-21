package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAuctionById implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuctionDao auctionDao = new AuctionDAO();
        Long id = Long.parseLong(request.getParameter("auctionID"));
        Auction auction = (Auction) auctionDao.findById(id);
        request.setAttribute("auction", auction);
        return auction;
    }
}