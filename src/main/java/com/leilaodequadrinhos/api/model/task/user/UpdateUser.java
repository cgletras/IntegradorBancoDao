package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUser extends BaseUserTask implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAO userDao = new UserDAO();
        User user = getUser(request);
        user.setUserID(Integer.parseInt(request.getParameter("userID")));
        user.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        userDao.update(user);
        return "User updated";
    }
}