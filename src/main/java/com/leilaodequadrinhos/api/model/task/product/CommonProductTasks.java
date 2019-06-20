package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;

import javax.servlet.http.HttpServletRequest;

abstract class CommonProductTasks {

    protected Produto getProduct(HttpServletRequest request, EstadoProdutoDao estadoProdutoDao, UserDao userDao) {
        Produto produto = new Produto();
        produto.setEditora(request.getParameter("Editora"));
        produto.setTitulo(request.getParameter("Titulo"));
        produto.setFormatoDoQuadrinho(request.getParameter("formato"));
        int pages = Integer.parseInt(request.getParameter("NumeroPaginas"));
        produto.setNumeroPaginas(pages);
        int weight = Integer.parseInt(request.getParameter("Peso"));
        produto.setPeso(weight);
        produto.setCapaImagem(request.getParameter("CapaImagem"));
        Long statusID = Long.parseLong(request.getParameter("statusID"));
        produto.setEstado(estadoProdutoDao.findById(statusID));
        Long userID = Long.parseLong(request.getParameter("userID"));
        produto.setUser((User) userDao.findById(userID));
        return produto;
    }
}
