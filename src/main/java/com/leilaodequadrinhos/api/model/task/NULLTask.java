package com.leilaodequadrinhos.api.model.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NULLTask implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        return request;
    }

    @Override
    public String toString() {
        return "NULL task";
    }
}