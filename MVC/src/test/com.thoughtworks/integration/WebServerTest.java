package com.thoughtworks.integration;

import com.thoughtworks.DispatchServlet;
import com.thoughtworks.WebServer;
import com.thoughtworks.view.FreemarkerViewResolver;

public class WebServerTest {
    public static void main(String[] args) throws Exception {
        WebServer webServer = new WebServer(8080,new DispatchServlet(null,new FreemarkerViewResolver("./MVC/resources/templates")));
        webServer.run();
    }
}
