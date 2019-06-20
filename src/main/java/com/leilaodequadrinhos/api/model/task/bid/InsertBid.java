package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.LanceDao;
import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LanceDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Lance;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertBid extends BaseBid implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LanceDao lanceDao = new LanceDAO();
        UserDAO userDao = new UserDAO();
        LeilaoDao leilaoDAO = new LeilaoDAO();
        Lance lance = getLance(request,userDao,leilaoDAO);
        lanceDao.insert(lance);
        return "Lance inserido com sucesso";
    }
}