package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserByID implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = new UserDAO();
        Long id = Long.parseLong(request.getParameter("userID"));
        if(!userDao.hasActiveAuction(id)){
            userDao.deleteById(id);
            return "Deleted user";
        } else {
            return "Users with active auctions cannot be deleted";
        }
    }
}