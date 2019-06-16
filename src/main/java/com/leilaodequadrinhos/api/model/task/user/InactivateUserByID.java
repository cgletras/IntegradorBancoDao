package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InactivateUserByID implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        UserDao userDao = DaoFactory.createUsuarioDao();
//        Integer id = Integer.parseInt(request.getParameter("userID"));
//        userDao.inactivate(id);
        return "Usuário desativado";
    }
}