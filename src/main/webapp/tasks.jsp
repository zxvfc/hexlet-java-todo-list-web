<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, org.example.TaskEntity" %>
<%
List<TaskEntity> tasks = (List<TaskEntity>) request.getAttribute("tasks");
%>
<html>
<head>
    <title>Hello!</title>
</head>
<body>
    <h1>Список задач</h1>
    <div><a href="/new-task.jsp">Создать задачу</a></div>
    <% if (tasks.isEmpty()) { %>
        <div>Пока нет задач</div>
    <% } %>
    <div>
        <% for (int i = 0; i < tasks.size(); i++ ) { %>
            <% TaskEntity task = tasks.get(i); %>
            <div>
                <div><%= task.name %> - <%= task.status %></div>
                <div><%= task.description %></div>
                <a href="/tasks/<%= task.id %>">Открыть задачу</a>
            </div>
            <br>
        <% } %>
    </div>
</body>
</html>