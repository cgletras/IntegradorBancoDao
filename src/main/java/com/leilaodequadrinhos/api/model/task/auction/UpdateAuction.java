package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.EstadoLeilaoDao;
import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoLeilaoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateAuction extends BaseAucionTask implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LeilaoDao leilaoDao = new LeilaoDAO();
        EstadoLeilaoDao estadoLeilaoDao = new EstadoLeilaoDAO();
        ProdutoDAO produtoDao = new ProdutoDAO();
        UserDAO userDao = new UserDAO();
        Leilao leilao = getLeilao(request, estadoLeilaoDao, produtoDao, userDao);
        leilaoDao.update(leilao);
        return "Leilao atualizado com sucesso";
    }
}
