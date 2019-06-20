package com.leilaodequadrinhos.api.model.task.character;

import com.leilaodequadrinhos.api.model.dao.PersonagemDao;
import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.PersonagemDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.entities.Personagem;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindCharactersByProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("productID"));
        PersonagemDao personagemDao = new PersonagemDAO();
        ProdutoDao produtoDao = new ProdutoDAO();
        List<Personagem> list = personagemDao.findAllByProduto((Produto) produtoDao.findById(id));
        request.setAttribute("personagens", list);
        return list;
    }
}
