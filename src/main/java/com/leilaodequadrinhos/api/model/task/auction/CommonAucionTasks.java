package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.EstadoLeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Leilao;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract class CommonAucionTasks {

    protected Leilao getLeilao(HttpServletRequest request, EstadoLeilaoDao estadoLeilaoDao, ProdutoDAO produtoDao, UserDAO userDao) throws ParseException {
        Long idPersonagem = Long.parseLong(request.getParameter("characterID"));
        Long idProduto = Long.parseLong(request.getParameter("productID"));
        Long idEstadoLeilao = Long.parseLong(request.getParameter("auctionStatusID"));
        Leilao leilao = new Leilao();
        leilao.setDuracao(Integer.parseInt(request.getParameter("duration")));
        String format = "dd/MM/yyyy";
        leilao.setDataInicio(new SimpleDateFormat(format).parse(request.getParameter("StartDate")));
        leilao.setValorInicial(Float.parseFloat(request.getParameter("BasePrice")));
        leilao.setValorAtual(leilao.getValorInicial());
        leilao.setLancePadrao(Float.parseFloat(request.getParameter("BaseBid")));
        leilao.setEstado(estadoLeilaoDao.findById(idEstadoLeilao));
        leilao.setProduto(produtoDao.findById(idProduto));
        leilao.setUser(userDao.findById(idPersonagem));
        return leilao;
    }
}
