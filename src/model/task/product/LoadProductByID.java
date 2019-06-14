package model.task.product;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadProductByID implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = DaoFactory.createProdutoDao();
        Integer id = Integer.parseInt(request.getParameter("productID"));
        Product product = productDao.findById(id);
        request.setAttribute("produto", product);
        return "produto carregado";
    }
}
