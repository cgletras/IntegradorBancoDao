package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllAuctionsByUser implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        AuctionDao auctionDao = new AuctionDAO();
        DAO userDao = new UserDAO();
        Long userID = Long.valueOf((((User) request.getSession().getAttribute("user")).getUserID()));
        List<Auction> auctions = auctionDao.findByUser((User) userDao.findById(userID));
        request.setAttribute("auctions", auctions);
        return auctions;
    }
}