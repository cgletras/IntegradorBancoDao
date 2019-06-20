package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;

import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertNewUser extends BaseUserTask implements Task {

    private static final boolean ACTIVE = true;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAO userDao = new UserDAO();
        User user = getUser(request);
        user.setStatus(ACTIVE);
        userDao.insert(user);
        return "Usuário cadastrado";
    }
}