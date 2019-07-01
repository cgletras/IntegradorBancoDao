package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*
//@param: auctionID
//@return:
// If auction exists: an Integer value showing the number of days left;
// If auction did not exist: null;

public class ReturnAuctionDurationById implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        AuctionDao auctionDao = new AuctionDAO();
        Long id = Long.parseLong(request.getParameter("auctionID"));
        Integer duration = auctionDao.returnAuctionDuration(id);
        request.setAttribute("duration", duration);
        return duration;
    }
}