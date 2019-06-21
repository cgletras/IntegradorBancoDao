package com.leilaodequadrinhos.api.model.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NULLTask implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @Override
    public String toString() {
        return "NULL task";
    }
}