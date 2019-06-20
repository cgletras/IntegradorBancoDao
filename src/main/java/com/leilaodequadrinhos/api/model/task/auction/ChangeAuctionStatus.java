package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.EstadoLeilaoDao;
import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoLeilaoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeAuctionStatus implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id_leilao = Integer.parseInt(request.getParameter("auctionID"));
        Long id_EstadoLeilao = Long.parseLong(request.getParameter("auctionStatusID"));

        LeilaoDao leilaoDao = new LeilaoDAO();
        EstadoLeilaoDao estadoLeilaoDao = new EstadoLeilaoDAO();
        EstadoLeilao estadoLeilao = estadoLeilaoDao.findById(id_EstadoLeilao);

        leilaoDao.changeStatusLeilao(id_leilao, estadoLeilao);

        return "Estado do leilão alterado";
    }
}
