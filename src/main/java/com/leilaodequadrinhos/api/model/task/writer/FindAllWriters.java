package com.leilaodequadrinhos.api.model.task.writer;

import com.leilaodequadrinhos.api.model.dao.WriterDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.WriterDAO;
import com.leilaodequadrinhos.api.model.entities.Writer;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllWriters implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        WriterDao writerDao = new WriterDAO();
        List writers = writerDao.findAll();
        request.setAttribute("writers", writers);
        return writers;
    }
}