package model.task.UserTasks;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindUserByID implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = DaoFactory.createUsuarioDao();
        Integer id = Integer.parseInt(request.getParameter("userID"));
        User user = userDao.findById(id);
        request.setAttribute("usuario", user);
        return "Usuario encontrado";
    }
}