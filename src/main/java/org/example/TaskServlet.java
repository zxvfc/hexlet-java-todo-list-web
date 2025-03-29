package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskServlet extends HttpServlet {

    Storage storage;

    TaskServlet(Storage storage) {
        this.storage = storage;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        System.out.println(pathInfo);
        if (pathInfo == null || pathInfo.equals("/")) {
            List<TaskEntity> tasks = storage.getAll();
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("/tasks.jsp").forward(request, response);
        } else {
            int id = Integer.parseInt(pathInfo.substring(1));
            TaskEntity task = storage.getById(id);
            if (task == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Задача с таким id не найдена");
                return;
            }
            request.setAttribute("task", task);
            request.getRequestDispatcher("/task.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        request.setCharacterEncoding("UTF-8");

        if (pathInfo == null) {
            TaskEntity task = new TaskEntity();
            task.name = request.getParameter("name");
            task.description = request.getParameter("description");
            task.status = request.getParameter("status");
            storage.save(task);
            System.out.println(task.name);
        } else {
            String[] path = pathInfo.split("/");
            String action = path[path.length - 1];
            if (action.equalsIgnoreCase("delete")) {
                int id = Integer.parseInt(path[1]);
                storage.delete(id);
            }
        }

        response.sendRedirect("/tasks");
    }

}
