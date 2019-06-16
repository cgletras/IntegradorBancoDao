package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DeleteUser implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = new UserDAO();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setStatus(Boolean.getBoolean(request.getParameter("status")));
        user.setEmail(request.getParameter("email"));
        String format = "dd/MM/yyyy";
        try {
            user.setDateOfBirth(new SimpleDateFormat(format).parse(request.getParameter("dateOfBirth")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPassword(request.getParameter("password"));
        userDao.delete(user);
        return null;
    }
}