package com.leilaodequadrinhos.api.model.task.character;

import com.leilaodequadrinhos.api.model.dao.CharacterDao;
import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.CharacterDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.entities.Character;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindCharactersByProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long productID = Long.parseLong(request.getParameter("productID"));
        CharacterDao characterDao = new CharacterDAO();
        ProductDao productDao = new ProductDAO();
        List<Character> characters = characterDao.findAllByProduct((Product) productDao.findById(productID));
        request.setAttribute("characters", characters);
        return characters;
    }
}