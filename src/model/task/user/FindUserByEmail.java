package model.task.UserTasks;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindUserByEmail implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = DaoFactory.createUsuarioDao();
        User user = userDao.findByEmail(request.getParameter("email"));
        request.setAttribute("usuario", user);
        return "Usuário encontrado";
    }
}