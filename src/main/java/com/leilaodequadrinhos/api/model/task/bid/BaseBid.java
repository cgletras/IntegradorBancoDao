package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Lance;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract class BaseBid {

    protected Lance getLance (HttpServletRequest request, UserDAO userDao, LeilaoDao leilaoDAO) throws ParseException {
        Lance lance = new Lance();
        String format = "dd/MM/yyyy";
        lance.setDataLance(new SimpleDateFormat(format).parse(request.getParameter("dateOfBid")));
        Long userID = Long.parseLong(request.getParameter("userID"));
        User user = userDao.findById(userID);
        lance.setUser(user);
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        Leilao leilao = (Leilao) leilaoDAO.findById(auctionID);
        lance.setValorLance(leilao.getLancePadrao());
        lance.setLeilao(leilao);
        return lance;
    }
}
