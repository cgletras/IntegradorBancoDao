package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract class BaseAuctionTask {

    protected Auction buildAuction(HttpServletRequest request, ProductDAO productDAO, UserDAO userDao) throws ParseException {
        Long userID = Long.valueOf((((User) request.getSession().getAttribute("user")).getUserID()));
        Long productID = Long.parseLong(request.getParameter("productID"));
        Auction auction = new Auction();
        auction.setDuration(Integer.parseInt(request.getParameter("duration")));
        String format = "dd/MM/yyyy";
        auction.setInitialDate(new SimpleDateFormat(format).parse(request.getParameter("initialDate")));
        auction.setInitialValue(Float.parseFloat(request.getParameter("initialValue")));
        auction.setCurrentValue(auction.getInitialValue());
        auction.setDefaultBid(Float.parseFloat(request.getParameter("baseBid")));
        auction.setProduct(productDAO.findById(productID));
        auction.setUser(userDao.findById(userID));
        return auction;
    }
}