package model.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Task {

    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}