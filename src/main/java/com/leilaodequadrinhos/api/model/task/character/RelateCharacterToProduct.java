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

public class RelateCharacterToProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PersonagemDao personagemDao = new PersonagemDAO();
        ProdutoDao produtoDao = new ProdutoDAO();
        Long idPersonagem = Long.parseLong(request.getParameter("characterID"));
        Long idProduto = Long.parseLong(request.getParameter("productID"));
        personagemDao.relacionarPersonagemProduto((Personagem) personagemDao.findById(idPersonagem), (Produto) produtoDao.findById(idProduto));
        return "Personagem relacionado ao quadrinho";
    }
}