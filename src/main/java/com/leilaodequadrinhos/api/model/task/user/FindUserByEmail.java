package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindUserByEmail implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        UserDao userDao = DaoFactory.createUsuarioDao();
//        User user = userDao.findByEmail(request.getParameter("email"));
//        request.setAttribute("usuario", user);
        return "Usuário encontrado";
    }
}