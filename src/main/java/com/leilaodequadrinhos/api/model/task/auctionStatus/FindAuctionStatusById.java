package com.leilaodequadrinhos.api.model.task.auctionStatus;

import com.leilaodequadrinhos.api.model.dao.EstadoLeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoLeilaoDAO;
import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAuctionStatusById implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("statusID"));
        EstadoLeilaoDao estadoLeilaoDao = new EstadoLeilaoDAO();
        EstadoLeilao estadoLeilao = estadoLeilaoDao.findById(id);
        request.setAttribute("estado", estadoLeilao);
        return estadoLeilao;
    }
}