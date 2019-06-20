package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Leilao;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract class BaseAuctionTask {

    protected Leilao getLeilao(HttpServletRequest request, ProdutoDAO produtoDao, UserDAO userDao) throws ParseException {
        Long userID = Long.parseLong(request.getParameter("userID"));
        Long productID = Long.parseLong(request.getParameter("productID"));
        Leilao leilao = new Leilao();
        leilao.setDuracao(Integer.parseInt(request.getParameter("duration")));
        String format = "dd/MM/yyyy";
        leilao.setDataInicio(new SimpleDateFormat(format).parse(request.getParameter("startDate")));
        leilao.setValorInicial(Float.parseFloat(request.getParameter("initialValue")));
        leilao.setValorAtual(leilao.getValorInicial());
        leilao.setLancePadrao(Float.parseFloat(request.getParameter("baseBid")));
        leilao.setProduto(produtoDao.findById(productID));
        leilao.setUser(userDao.findById(userID));
        return leilao;
    }
}