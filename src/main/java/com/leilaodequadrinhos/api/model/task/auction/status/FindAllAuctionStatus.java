package com.leilaodequadrinhos.api.model.task.auction.status;

import com.leilaodequadrinhos.api.model.dao.AuctionStatusDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionStatusDAO;
import com.leilaodequadrinhos.api.model.entities.AuctionStatus;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllAuctionStatus implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuctionStatusDao auctionStatusDao = new AuctionStatusDAO();
        List<AuctionStatus> listAuctionStatus = auctionStatusDao.findAll();
        request.setAttribute("auctionStatus", listAuctionStatus);
        return listAuctionStatus;
    }
}