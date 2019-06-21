package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.BidDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.BidDAO;
import com.leilaodequadrinhos.api.model.entities.Bid;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindBidByID implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        BidDao bidDao = new BidDAO();
        Bid bid = (Bid) bidDao.findById(Long.parseLong(request.getParameter("bidID")));
        request.setAttribute("bid", bid);
        return bid;
    }
}