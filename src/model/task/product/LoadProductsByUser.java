package model.task.product;

import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.dao.UserDao;
import model.entities.Produto;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoadProductsByUser implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        UserDao userDao = DaoFactory.createUsuarioDao();
        Integer id = Integer.parseInt(request.getParameter("userID"));
        List<Produto> userProdutoList = produtoDao.findAllByUser(userDao.findById(id));
        request.setAttribute("produtos", userProdutoList);
        return "produtos do usuário";
    }
}
