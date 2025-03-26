package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.sql.SQLException;

public class EmbedTomcatApp {
    public static void main(String[] args) throws LifecycleException, SQLException {
        int port = 8080;

        Storage storage = new Storage();
        TaskServlet taskServlet = new TaskServlet(storage);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        Context context = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());

        tomcat.addServlet("", "task-servlet", taskServlet);
        context.addServletMappingDecoded("/tasks/*", "task-servlet");

        tomcat.start();
        tomcat.getConnector();
        tomcat.getServer().await();
    }
}