package com.leilaodequadrinhos.api.model.task.character;

import com.leilaodequadrinhos.api.model.dao.CharacterDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.CharacterDAO;
import com.leilaodequadrinhos.api.model.entities.Character;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllCharacters implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterDao characterDao = new CharacterDAO();
        List<Character> characters = characterDao.findAll();
        request.setAttribute("characters", characters);
        return characters;
    }
}