package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

public class UpdateUser implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        UserDao userDao = DaoFactory.createUsuarioDao();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setStatus(Boolean.getBoolean(request.getParameter("status")));
        user.setEmail(request.getParameter("email"));
        String format = "dd/MM/yyyy";
        user.setDateOfBirth(new SimpleDateFormat(format).parse(request.getParameter("dateOfBirth")));
        user.setPassword(request.getParameter("password"));
//        userDao.update(user);
        return "Usuário atualizado";
    }
}