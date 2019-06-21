package com.leilaodequadrinhos.api.model.task.character;

import com.leilaodequadrinhos.api.model.dao.CharacterDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.CharacterDAO;
import com.leilaodequadrinhos.api.model.entities.Character;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCharacter implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        CharacterDao characterDao = new CharacterDAO();
        Character character = new Character();
        character.setName(request.getParameter("characterName"));
        characterDao.insert(character);
        return "Registered character";
    }
}