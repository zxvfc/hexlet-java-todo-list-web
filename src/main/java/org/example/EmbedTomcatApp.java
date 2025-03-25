package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbedTomcatApp {
    public static void main(String[] args) throws LifecycleException {
        int port = 8080;

        TaskServlet taskServlet = new TaskServlet();

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