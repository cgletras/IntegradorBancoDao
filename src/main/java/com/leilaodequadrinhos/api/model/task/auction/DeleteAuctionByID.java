package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAuctionByID implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        AuctionDao auctionDao = new AuctionDAO();
        auctionDao.deleteById(auctionID);
        return "This auction has been deleted";
    }
}