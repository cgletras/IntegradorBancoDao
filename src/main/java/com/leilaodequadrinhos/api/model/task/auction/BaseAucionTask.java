package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.EstadoLeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Leilao;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract class BaseAucionTask {

    protected Leilao getLeilao(HttpServletRequest request, EstadoLeilaoDao estadoLeilaoDao, ProdutoDAO produtoDao, UserDAO userDao) throws ParseException {
        Long characterID = Long.parseLong(request.getParameter("characterID"));
        Long productID = Long.parseLong(request.getParameter("productID"));
        Long auctionStatusID = Long.parseLong(request.getParameter("auctionStatusID"));
        Leilao leilao = new Leilao();
        leilao.setDuracao(Integer.parseInt(request.getParameter("duration")));
        String format = "dd/MM/yyyy";
        leilao.setDataInicio(new SimpleDateFormat(format).parse(request.getParameter("startDate")));
        leilao.setValorInicial(Float.parseFloat(request.getParameter("basePrice")));
        leilao.setValorAtual(leilao.getValorInicial());
        leilao.setLancePadrao(Float.parseFloat(request.getParameter("baseBid")));
        leilao.setEstado(estadoLeilaoDao.findById(auctionStatusID));
        leilao.setProduto(produtoDao.findById(productID));
        leilao.setUser(userDao.findById(characterID));
        return leilao;
    }
}