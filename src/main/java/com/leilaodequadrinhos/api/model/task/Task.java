package com.leilaodequadrinhos.api.model.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Task {

    Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}