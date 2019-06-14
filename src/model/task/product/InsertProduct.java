package model.task.product;

import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.dao.UserDao;
import model.entities.Produto;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertProduct implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        UserDao userDao = DaoFactory.createUsuarioDao();
        Produto produto = new Produto();
        produto.setEditora(request.getParameter("Editora"));
        produto.setTitulo(request.getParameter("Titulo"));
        produto.setFormatoDoQuadrinho(request.getParameter("formato"));
        Integer pages = Integer.parseInt(request.getParameter("NumeroPaginas"));
        produto.setNumeroPaginas(pages);
        Integer weight = Integer.parseInt(request.getParameter("Peso"));
        produto.setPeso(weight);
        produto.setCapaImagem(request.getParameter("CapaImagem"));
        Integer statusID = Integer.parseInt(request.getParameter("statusID"));
        produto.setEstado();
        produto.setUser(userDao.);
        produtoDao.insertProduct(produto);
        return "Produto Inserido";
    }
}
