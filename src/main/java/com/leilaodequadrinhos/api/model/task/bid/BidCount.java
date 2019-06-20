package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.LanceDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LanceDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BidCount implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("auctionID"));
        LanceDao lanceDao = new LanceDAO();
        Long bidCount = lanceDao.BidCount(id);
        request.setAttribute("lances", bidCount);
        return bidCount;
    }
}