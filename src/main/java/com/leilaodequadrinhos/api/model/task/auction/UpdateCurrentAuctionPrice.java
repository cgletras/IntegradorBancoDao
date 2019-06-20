package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCurrentAuctionPrice implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LeilaoDao leilaoDao = new LeilaoDAO();
        Leilao leilao = new Leilao();
        leilao.setIdLeilao(Long.parseLong(request.getParameter("id")));
        leilao.setLancePadrao(Float.parseFloat(request.getParameter("baseBid")));
        leilaoDao.updateValorAtual(leilao);
        return "O valor atual do leilão foi atualizado";
    }
}
