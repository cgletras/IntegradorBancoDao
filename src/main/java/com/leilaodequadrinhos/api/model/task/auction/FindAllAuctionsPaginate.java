package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllAuctionsPaginate implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        AuctionDao auctionDao = new AuctionDAO();
        Integer limit = request.getParameter("limit") != null ? Integer.parseInt(request.getParameter("limit")) : 100;
        Integer offset = request.getParameter("offset") != null ? Integer.parseInt(request.getParameter("offset")) : 0;

        List auctions = auctionDao.findAllPaginate(limit, offset);
        request.setAttribute("auctions", auctions);
        return auctions;
    }
}