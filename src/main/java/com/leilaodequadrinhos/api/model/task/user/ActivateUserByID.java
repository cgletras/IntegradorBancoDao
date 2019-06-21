package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActivateUserByID implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = new UserDAO();
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        userDao.activate(userID);
        return "User enabled";
    }
}