package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EscritorDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoLeilaoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EstadoProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LanceDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.PersonagemDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProdutoDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;

public class DaoFactory {

    public static EscritorDao createEscritorDao() {
        return new EscritorDAO(DB.getConnection());
    }

    public static EstadoLeilaoDao createEstadoLeilaoDao() {
        return new EstadoLeilaoDAO(DB.getConnection());
    }

    public static EstadoProdutoDao createEstadoProdutoDao() {
        return new EstadoProdutoDAO(DB.getConnection());
    }

    public static LanceDao createLanceDao() {
        return new LanceDAO(DB.getConnection());
    }

    public static LeilaoDao createLeilaoDao() {
        return new LeilaoDAO(DB.getConnection());
    }

    public static PersonagemDao createPersonagemDao() {
        return new PersonagemDAO(DB.getConnection());
    }

    public static ProdutoDao createProdutoDao() {
        return new ProdutoDAO(DB.getConnection());
    }

//    public static UserDao createUsuarioDao() {
//        return new UserDAO(DB.getConnection());
//    }

}