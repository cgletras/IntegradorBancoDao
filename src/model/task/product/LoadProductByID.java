package model.task.product;

import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.entities.Produto;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadProductByID implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        Integer id = Integer.parseInt(request.getParameter("productID"));
        Produto produto = produtoDao.findById(id);
        request.setAttribute("produto", produto);
        return "produto carregado";
    }
}
