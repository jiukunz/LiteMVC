package com.thoughtworks;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class WebServer {

    private int port;
    private HttpServlet servlet;

    public WebServer(int port, HttpServlet servlet) {
        this.port = port;
        this.servlet = servlet;
    }

    public void start() throws Exception {
        Server server = new Server(port);
        Context context = new Context(server, "/");
        context.addServlet(new ServletHolder(servlet), "/*");
        server.start();
    }
}