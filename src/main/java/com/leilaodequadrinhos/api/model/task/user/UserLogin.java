package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogin implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        UserDao userDao = DaoFactory.createUsuarioDao();
//        User user = userDao.findByEmail(request.getParameter("email"));
//
//        if (user == null) {
//            return "Usuario ou senha incorretos";
//        } else if (user.getPassword().equals(request.getParameter("password"))){
//            return "Logado";
//        } else {
//            return "Usuario ou senha incorreta";
//        }
        return "oi";
    }
}
