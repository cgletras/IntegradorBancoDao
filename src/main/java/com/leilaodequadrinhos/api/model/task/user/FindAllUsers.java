package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllUsers implements Task {

    @Override
    public List<User> execute(HttpServletRequest request, HttpServletResponse response) {
        DAO dao = new UserDAO();
        List<User> userList = dao.findAll();
        request.setAttribute("usuarios", userList);
        return userList;
    }
}