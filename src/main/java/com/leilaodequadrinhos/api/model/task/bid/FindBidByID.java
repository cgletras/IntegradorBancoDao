package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.LanceDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LanceDAO;
import com.leilaodequadrinhos.api.model.entities.Lance;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindBidByID implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LanceDao lanceDao = new LanceDAO();
        Lance lance = (Lance) lanceDao.findById(Long.parseLong(request.getParameter("bidID")));
        request.setAttribute("lance",lance);
        return lance;
    }
}