<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, org.example.TaskEntity" %>
<%
TaskEntity task = (TaskEntity) request.getAttribute("task");
%>
<html>
<head>
    <title>Hello!</title>
</head>
<body>
    <h1>Задача № <%= task.id %></h1>
    <div>
        Имя: <%= task.name %>
        <br>
        Описание: <%= task.description %>
        <br>
        Статус: <%= task.status %>
        <br>
        <form action="/tasks/<%= task.id %>/delete" method="post">
            <button type="submit">Удалить</button>
        </form>
    </div>
</body>
</html>