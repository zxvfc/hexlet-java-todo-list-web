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
    <div>
        <% for (int i = 0; i < tasks.size(); i++ ) { %>
            <div><%= tasks.get(i).name %></div>
        <% } %>
    </div>
</body>
</html>