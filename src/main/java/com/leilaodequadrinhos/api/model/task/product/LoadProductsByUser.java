package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoadProductsByUser implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
//        UserDao userDao = DaoFactory.createUsuarioDao();
        Integer id = Integer.parseInt(request.getParameter("userID"));
//        List<Produto> userProdutoList = produtoDao.findAllByUser(userDao.findById(id));
//        request.setAttribute("produtos", userProdutoList);
        return "produtos do usuário";
    }
}
