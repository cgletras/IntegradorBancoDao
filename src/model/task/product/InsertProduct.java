package model.task.product;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.dao.UserDao;
import model.entities.Product;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertProduct implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = DaoFactory.createProdutoDao();
        UserDao userDao = DaoFactory.createUsuarioDao();
        Product product = new Product();
        product.setEditora(request.getParameter("Editora"));
        product.setTitulo(request.getParameter("Titulo"));
        product.setFormatoDoQuadrinho(request.getParameter("formato"));
        Integer pages = Integer.parseInt(request.getParameter("NumeroPaginas"));
        product.setNumeroPaginas(pages);
        Integer weight = Integer.parseInt(request.getParameter("Peso"));
        product.setPeso(weight);
        product.setCapaImagem(request.getParameter("CapaImagem"));
        Integer statusID = Integer.parseInt(request.getParameter("statusID"));
        product.setEstado();
        product.setUser(userDao.);
        productDao.insertProduct(product);
        return "Produto Inserido";
    }
}
