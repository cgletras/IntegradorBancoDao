package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.BidDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.BidDAO;
import com.leilaodequadrinhos.api.model.entities.Bid;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindBidsByUser implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BidDao bidDao = new BidDAO();
        List<Bid> bids = bidDao.findBidsByUser(Long.parseLong("userID"));
        request.setAttribute("bids", bids);
        return bids;
    }
}