package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Leilao;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllAuctionsByUser implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        LeilaoDao leilaoDao = new LeilaoDAO();
        DAO userDao = new UserDAO();
        Long id_usuario = Long.parseLong(request.getParameter("userID"));

        List<Leilao> leiloes = leilaoDao.findByUser((User) userDao.findById(id_usuario));
        request.setAttribute("leiloes", leiloes);

        return leiloes;
    }
}
