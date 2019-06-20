package com.leilaodequadrinhos.api.model.task.writer;

import com.leilaodequadrinhos.api.model.dao.EscritorDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.EscritorDAO;
import com.leilaodequadrinhos.api.model.entities.Escritor;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllWriters implements Task {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EscritorDao escritorDao = new EscritorDAO();
        List<Escritor> list = escritorDao.findAll();
        request.setAttribute("escritores", list);
        return list;
    }
}
