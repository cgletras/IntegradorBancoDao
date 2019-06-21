package com.leilaodequadrinhos.api.model.task.writer;

import com.leilaodequadrinhos.api.model.dao.WriterDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.WriterDAO;
import com.leilaodequadrinhos.api.model.entities.Writer;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertWriter implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        WriterDao writerDao = new WriterDAO();
        Writer writer = new Writer();
        writer.setName(request.getParameter("writerName"));
        writerDao.insert(writer);
        return "Registered writer";
    }
}