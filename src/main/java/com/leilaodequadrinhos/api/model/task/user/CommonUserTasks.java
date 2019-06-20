package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract class CommonUserTasks {

    protected User getUser(HttpServletRequest request) throws ParseException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setStatus(true);
        user.setEmail(request.getParameter("email"));
        user.setState(request.getParameter("state"));
        user.setCity(request.getParameter("city"));
        String format = "dd/MM/yyyy";
        user.setDateOfBirth(new SimpleDateFormat(format).parse(request.getParameter("dateOfBirth")));
        user.setPassword(request.getParameter("password"));
        return user;
    }
}
