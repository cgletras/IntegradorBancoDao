package com.leilaodequadrinhos.api.model.task.user;

<<<<<<< HEAD
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.User;
=======
import com.leilaodequadrinhos.api.model.entities.Produto;
>>>>>>> ajustes_produto
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindUserByEmail implements Task {

    @Override
    public User execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = new UserDAO();
        User user = userDao.findByEmail(request.getParameter("email"));
        request.setAttribute("usuario", user);
        return user;
    }
}