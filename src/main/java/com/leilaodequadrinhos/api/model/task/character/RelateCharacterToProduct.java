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

public class RelateCharacterToProduct implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        CharacterDao characterDao = new CharacterDAO();
        ProductDao productDao = new ProductDAO();
        Long characterID = Long.parseLong(request.getParameter("characterID"));
        Long productID = Long.parseLong(request.getParameter("productID"));
        characterDao.relateCharacterToProduct((Character) characterDao.findById(characterID), (Product) productDao.findById(productID));
        return "Character related to the comic";
    }
}