package controller;

import model.task.Task;
import model.task.TaskFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/*")
public class Controller extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			Task task = TaskFactory.getTask(request);
			String view = task.execute(request, response);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + view + ".jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Erro ao executar tarefa.", e);
		}
	}
}
