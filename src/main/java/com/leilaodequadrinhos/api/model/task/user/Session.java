package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Session implements Task {

    @Override
    public Boolean execute(HttpServletRequest request, HttpServletResponse response) {
        return !(request.getSession().getAttribute("user") == null);
    }
}