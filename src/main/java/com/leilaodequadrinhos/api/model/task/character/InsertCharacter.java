package com.leilaodequadrinhos.api.model.task.character;

import com.leilaodequadrinhos.api.model.dao.PersonagemDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.PersonagemDAO;
import com.leilaodequadrinhos.api.model.entities.Personagem;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCharacter implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PersonagemDao personagemDao = new PersonagemDAO();
        Personagem personagem = new Personagem();
        personagem.setNome(request.getParameter("nomePersonagem"));
        personagemDao.insert(personagem);
        return "Personagem cadastrado";
    }
}
