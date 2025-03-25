<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Hello!</title>
</head>
<body>
    <h1>Создать задачу</h1>
    <form action="/tasks" method="post">
        <label>
            Имя задачи: <input type="text" name="name" required>
        </label>
        <br>
        <label>
            Описание задачи: <input type="textarea" name="description" required>
        </label>
        <br>
        <label>
            Статус задачи: <select name="status" >
                <option value="Pending">Pending</option>
                <option value="In Progress">In Progress</option>
                <option value="Complete">Complete</option>
            </select>
        </label>
        <br>
        <button type="submit">Создать</button>
    </form>
</body>
</html>