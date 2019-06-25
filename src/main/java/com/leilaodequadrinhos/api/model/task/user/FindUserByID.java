package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindUserByID implements Task {

    @Override
    public User execute(HttpServletRequest request, HttpServletResponse response) {
        DAO userDao = new UserDAO();
        Long id = Long.valueOf((((User) request.getSession().getAttribute("user")).getUserID()));
        User user = (User) userDao.findById(id);
        request.setAttribute("user", user);
        return user;
    }
}