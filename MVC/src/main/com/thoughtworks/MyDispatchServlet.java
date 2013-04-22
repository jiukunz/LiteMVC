package com.thoughtworks;

import com.thoughtworks.annotation.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyDispatchServlet extends HttpServlet {

    private Injector injector;
    private ViewResolver viewResolver;

    public MyDispatchServlet(Injector injector, ViewResolver viewResolver) {
        this.injector = injector;
        this.viewResolver = viewResolver;
    }

    @Inject


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //path => controller & action & model class
        //model class + parameter => model

        //injector + controller class => inject.newInstance(xxx.class)
        //controller.action(model) => modelandview


        //resolver(modelandview) => render
        super.doPost(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }
}

