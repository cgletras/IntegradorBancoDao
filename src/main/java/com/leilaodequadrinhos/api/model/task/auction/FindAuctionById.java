package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAuctionById implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        LeilaoDao leilaoDao = new LeilaoDAO();
        Long id = Long.parseLong(request.getParameter("auctionID"));
        Leilao leilao = (Leilao) leilaoDao.findById(id);
        request.setAttribute("leilao", leilao);

        return leilao;
    }
}
