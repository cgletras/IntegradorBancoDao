package model.task.UserTasks;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllUsers implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = DaoFactory.createUsuarioDao();
        List<User> userList = userDao.findAll();
        request.setAttribute("usuarios", userList);
        return "listagemUsuarios";
    }
}