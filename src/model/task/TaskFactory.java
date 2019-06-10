package model.task;

import model.task.UserTasks.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class TaskFactory {

    private static final Map<String, Task> tasks = new HashMap<>();

    static {
        tasks.put("POST/insertUser", new InsertNewUser());
        tasks.put("POST/activateUser", new ActivateUserByID());
        tasks.put("POST/inactivateUser", new InactivateUserByID());
        tasks.put("POST/updateUser", new UpdateUser());
        tasks.put("GET/findAllUsers", new FindAllUsers());
        tasks.put("GET/findUserByID", new FindUserByID());
        tasks.put("GET/findUserByEmail", new FindUserByEmail());
    }

    public static Task getTask(HttpServletRequest request) {
        Task task = tasks.get(request.getMethod() + request.getPathInfo());
        if (task != null)
            return task;
        else
            return new NULLTask();
    }
}