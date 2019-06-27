package com.leilaodequadrinhos.api.controller;

import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;
import com.leilaodequadrinhos.api.model.task.TaskFactory;
import com.leilaodequadrinhos.api.util.Json;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
@WebServlet("/controller/*")
public class FrontController extends HttpServlet implements Filter {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Object> responseBodyObject = new HashMap<>();
        Task task = TaskFactory.getTask(request);

        try {
            responseBodyObject.put("data", task.execute(request, response));
            responseBodyObject.put("user", request.getSession().getAttribute("user"));

            String jResponse = Json.objectToJson(responseBodyObject);

            PrintWriter out = response.getWriter();
            out.println(jResponse);
        } catch (DbException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
        } catch (Exception e) {
            throw new ServletException("Error executing task", e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        setAccessControlHeaders(request, response);

        HttpSession session = request.getSession(false);
        String loginPath = "/login";
        String publicPath = "/public";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getPathInfo().equals(loginPath);
        boolean publicRequest = request.getPathInfo().startsWith(publicPath);
        boolean corsRequest = request.getMethod().equalsIgnoreCase("OPTIONS");

        if (loggedIn || loginRequest || publicRequest || corsRequest) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void setAccessControlHeaders(HttpServletRequest request, HttpServletResponse response) {
        List<String> incomingURLs = Arrays.asList(request.getServletContext().getInitParameter("incomingURLs").trim().split(";"));
        String clientOrigin = request.getHeader("origin");

        if (incomingURLs.indexOf(clientOrigin) != -1) {
            response.setHeader("Access-Control-Allow-Origin", clientOrigin);
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, cache-control, sotero");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        }
    }
}