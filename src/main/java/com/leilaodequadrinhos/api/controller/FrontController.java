package com.leilaodequadrinhos.api.controller;

import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.task.Task;
import com.leilaodequadrinhos.api.model.task.TaskFactory;
import com.leilaodequadrinhos.api.util.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller/*")
public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
			Task task = TaskFactory.getTask(request);
			String jResponse = Json.objectToJson(task.execute(request, response));

            response.setContentType("application/json");

            PrintWriter out = response.getWriter();
            out.println(jResponse);

		} catch (DbException e) {
            System.out.println(e.getStackTrace().toString());
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
        } catch (Exception e) {
			throw new ServletException("Erro ao executar tarefa.", e);
		}

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
