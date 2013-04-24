package com.thoughtworks.main;

import com.thoughtworks.DispatchServlet;
import com.thoughtworks.FakeGuice;
import com.thoughtworks.Injector;
import com.thoughtworks.WebServer;
import com.thoughtworks.servlet.BicycleServlet;
import com.thoughtworks.servlet.BicycleServletModule;

public class BicycleServer {
    public static void main(String[] args) throws Exception {
        Injector injector = FakeGuice.createInjector(new BicycleServletModule());

        DispatchServlet servlet = (BicycleServlet)injector.getInstance(BicycleServlet.class);
// equal       DispatchServlet servlet1 = new DispatchServlet(new BicycleModule(),new FreemarkerResolver());

        WebServer webServer = new WebServer(8080, servlet);

        webServer.start();
    }
}
