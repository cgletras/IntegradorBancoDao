package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract class BaseAuctionTask {

    private static final long INAUCTION = 3;

    protected Auction buildAuction(HttpServletRequest request, ProductDAO productDAO, UserDAO userDao) throws ParseException {
        Long userID = Long.valueOf((((User) request.getSession().getAttribute("user")).getUserID()));
        Long productID = Long.parseLong(request.getParameter("productID"));
        ProductStatusDAO productStatusDAO = new ProductStatusDAO();
        Auction auction = new Auction();
        auction.setDuration(Integer.parseInt(request.getParameter("duration")));
        String format = "dd/MM/yyyy";
        auction.setInitialDate(new SimpleDateFormat(format).parse(request.getParameter("initialDate")));
        auction.setInitialValue(Float.parseFloat(request.getParameter("initialValue")));
        auction.setCurrentValue(auction.getInitialValue());
        auction.setDefaultBid(Float.parseFloat(request.getParameter("baseBid")));
        auction.setProduct(productDAO.findById(productID));
        productDAO.changeStatusProduct(productID, productStatusDAO.findById(INAUCTION));
        auction.setUser(userDao.findById(userID));
        return auction;
    }
}