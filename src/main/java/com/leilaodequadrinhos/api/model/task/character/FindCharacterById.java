package com.leilaodequadrinhos.api.model.task.character;

import com.leilaodequadrinhos.api.model.dao.CharacterDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.CharacterDAO;
import com.leilaodequadrinhos.api.model.entities.Character;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindCharacterById implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long characterID = Long.parseLong(request.getParameter("characterID"));
        CharacterDao characterDao = new CharacterDAO();
        Character character = (Character) characterDao.findById(characterID);
        request.setAttribute("character", character);
        return character;
    }
}