package com.leilaodequadrinhos.api.controller;

import com.leilaodequadrinhos.api.model.task.Task;
import com.leilaodequadrinhos.api.model.task.TaskFactory;
import com.leilaodequadrinhos.api.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller/*")
public class FrontController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
			Task task = TaskFactory.getTask(request);
			String jResponse = Json.objectToJson(task.execute(request, response));

            response.setContentType("application/json");

            PrintWriter out = response.getWriter();
            out.println(jResponse);

		} catch (Exception e) {
			throw new ServletException("Erro ao executar tarefa.", e);
		}





    }
}
