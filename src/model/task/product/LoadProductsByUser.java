package model.task.product;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.dao.UserDao;
import model.entities.Product;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoadProductsByUser implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = DaoFactory.createProdutoDao();
        UserDao userDao = DaoFactory.createUsuarioDao();
        Integer id = Integer.parseInt(request.getParameter("userID"));
        List<Product> userProductList = productDao.findAllByUser(userDao.findById(id));
        request.setAttribute("produtos", userProductList);
        return "produtos do usuário";
    }
}
