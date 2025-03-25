package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET REQUEST!!!");

        List<TaskEntity> tasks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TaskEntity task = new TaskEntity();
            task.name = "Task name-" + i;
            task.description = "Task desc-" + i;
            task.status = "Pending";
            tasks.add(task);
        }
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/tasks.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskName = request.getParameter("name");
        String taskDescription = request.getParameter("description");
        String taskStatus = request.getParameter("status");

        System.out.println("Name: " + taskName);
        System.out.println("Desc: " + taskDescription);
        System.out.println("Status: " + taskStatus);
        response.sendRedirect("/tasks");
    }

}
