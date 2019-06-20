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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/controller/*")
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        setAccessControlHeaders(response);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> responseBodyObject = new HashMap<>();


        try {
            verifyLogin(request, response, responseBodyObject);
            String jResponse = Json.objectToJson(responseBodyObject);

            PrintWriter out = response.getWriter();
            out.println(jResponse);
        } catch (DbException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
        } catch (Exception e) {
            throw new ServletException("Erro ao executar tarefa.", e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, cache-control");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
    }

    private void verifyLogin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> responseBodyObject) throws Exception {
        if (!((request.getMethod() + request.getPathInfo()).equalsIgnoreCase("POST/login")) && request.getSession().getAttribute("user") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            Task task = TaskFactory.getTask(request);
            responseBodyObject.put("data", task.execute(request, response));
        }

        responseBodyObject.put(
                "user", request.getSession().getAttribute("user")
        );
    }
}