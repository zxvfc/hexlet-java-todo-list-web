package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    final static String TASKS = "tasks";
    final static String ID = "id";
    final static String NAME = "name";
    final static String DESC = "desc";
    final static String STATUS = "status";

    Connection connection;

    Storage() throws SQLException {
        String connectionUrl = "jdbc:h2:./data/todoDB";
        String userName = "";
        String password = "";

        connection = DriverManager.getConnection(connectionUrl, userName, password);
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + TASKS + " (" +
                        ID + " INT AUTO_INCREMENT PRIMARY KEY, " +
                        NAME + " VARCHAR(60) NOT NULL DEFAULT '', " +
                        DESC + " VARCHAR(255) NOT NULL DEFAULT '', " +
                        STATUS + " VARCHAR(32) NOT NULL DEFAULT '' " +
                        ")"
        );
    }

    TaskEntity getById(int id) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + TASKS + " WHERE " + ID + " = " + id);
            TaskEntity task = new TaskEntity();
            resultSet.next();
            task.id = resultSet.getInt(ID);
            task.name = resultSet.getString(NAME);
            task.description = resultSet.getString(DESC);
            task.status = resultSet.getString(STATUS);
            return task;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<TaskEntity> getAll() {
        List<TaskEntity> allTasks = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + TASKS);
            while (resultSet.next()) {
                TaskEntity task = new TaskEntity();
                task.id = resultSet.getInt(ID);
                task.name = resultSet.getString(NAME);
                task.description = resultSet.getString(DESC);
                task.status = resultSet.getString(STATUS);
                allTasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allTasks;
    }

    void delete(int id) {
        try {
            connection.createStatement().execute("DELETE FROM " + TASKS + " WHERE " + ID + " = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void save(TaskEntity task) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO " + TASKS + " (" +
                            NAME + ", " +
                            DESC + ", " +
                            STATUS + ")" +
                            "VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, task.name);
            preparedStatement.setString(2, task.description);
            preparedStatement.setString(3, task.status);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
