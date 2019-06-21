package com.leilaodequadrinhos.api.model.task.auction.status;

import com.leilaodequadrinhos.api.model.dao.AuctionStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionStatusDAO;
import com.leilaodequadrinhos.api.model.entities.AuctionStatus;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAuctionStatusById implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long statusID = Long.parseLong(request.getParameter("statusID"));
        AuctionStatusDao auctionStatusDao = new AuctionStatusDAO();
        AuctionStatus auctionStatus = auctionStatusDao.findById(statusID);
        request.setAttribute("status", auctionStatus);
        return auctionStatus;
    }
}