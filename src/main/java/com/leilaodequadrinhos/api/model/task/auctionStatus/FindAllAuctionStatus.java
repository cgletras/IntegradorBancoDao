package com.leilaodequadrinhos.api.model.task.auctionStatus;

import com.leilaodequadrinhos.api.model.dao.EstadoLeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoLeilaoDAO;
import com.leilaodequadrinhos.api.model.entities.EstadoLeilao;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllAuctionStatus implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EstadoLeilaoDao estadoLeilaoDao = new EstadoLeilaoDAO();
        List<EstadoLeilao> list = estadoLeilaoDao.findAll();
        request.setAttribute("estados-leilao", list);
        return list;
    }
}
