package com.leilaodequadrinhos.api.model.task;

import com.leilaodequadrinhos.api.model.task.product.FindProductByID;
import com.leilaodequadrinhos.api.model.task.user.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class TaskFactory {

    private static final Map<String, Task> tasks = new HashMap<>();

    static {
        tasks.put("POST/activate-user", new ActivateUserByID());
        tasks.put("POST/delete-user", new DeleteUserByID());
        tasks.put("GET/find-all-users", new FindAllUsers());
        tasks.put("GET/find-user-by-email", new FindUserByEmail());
        tasks.put("GET/find-user-by-id", new FindUserByID());
        tasks.put("POST/insert-user", new InsertNewUser());
        tasks.put("POST/update-user", new UpdateUser());
        tasks.put("POST/login", new UserLogin());
        tasks.put("GET/find-product-by-id", new FindProductByID());
    }

    public static Task getTask(HttpServletRequest request) {
        Task task = tasks.get(request.getMethod() + request.getPathInfo());
        if (task != null)
            return task;
        else
            return new NULLTask();
    }
}