package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.BidDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.BidDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BidCount implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        BidDao bidDao = new BidDAO();
        Long bidCount = bidDao.BidCount(auctionID);
        request.setAttribute("bids", bidCount);
        return bidCount;
    }
}