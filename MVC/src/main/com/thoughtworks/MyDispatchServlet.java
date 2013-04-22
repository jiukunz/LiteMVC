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
        String path = request.getPathInfo();
        String[] controllerAndMethod = path.split("/");
        String controllerName = "com.thoughtworks.app.controllers." + capitalize(controllerAndMethod[1]) + "Controller";
        String methodName = controllerAndMethod[2];

        try {
            Class controllerClass = Class.forName(controllerName);
            Object controller = injector.getInstance(controllerClass);
            ModelAndView mv = (ModelAndView)controller.getClass().getMethod(methodName).invoke(controller);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
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

