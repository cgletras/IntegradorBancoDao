package com.leilaodequadrinhos.api.model.task.character;

import com.leilaodequadrinhos.api.model.dao.PersonagemDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.PersonagemDAO;
import com.leilaodequadrinhos.api.model.entities.Personagem;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllCharacters implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PersonagemDao personagemDao = new PersonagemDAO();
        List<Personagem> list = personagemDao.findAll();
        request.setAttribute("personagens", list);
        return list;
    }
}
