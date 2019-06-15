package model.task.user;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogin implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = DaoFactory.createUsuarioDao();
        User user = userDao.findByEmail(request.getParameter("email"));

        if (user == null) {
            return "Usuario ou senha incorretos";
        } else if (user.getPassword().equals(request.getParameter("password"))){
            return "Logado";
        } else {
            return "Usuario ou senha incorreta";
        }
    }
}
