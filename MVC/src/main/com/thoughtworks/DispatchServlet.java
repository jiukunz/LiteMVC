package com.thoughtworks;

import com.thoughtworks.view.ViewResolver;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class DispatchServlet extends HttpServlet {

    private Configuration config;
    private Injector injector;
    private ViewResolver viewResolver;

    public DispatchServlet(Injector injector, ViewResolver viewResolver) {
        this.injector = injector;
        this.viewResolver = viewResolver;
    }

    @Override
    public void init() throws ServletException {
        config = new Configuration();
        try {
            config.setDirectoryForTemplateLoading(new File("./MVC/src/resources/templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map root = newHashMap();
        root.put("message", "Hello World!");
        Template template;
        System.out.println(System.getProperty("user.dir"));
        template = config.getTemplate("test.ftl");
        response.setContentType("text/html; charset=" + template.getEncoding());
        Writer out = response.getWriter();
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            throw new ServletException(
                    "Error while processing FreeMarker template", e);
        }
//        String path = request.getPathInfo();
//        String[] controllerAndMethod = path.split("/");
//        String controllerName = "com.thoughtworks.app.controllers." + capitalize(controllerAndMethod[1]) + "Controller";
//        String methodName = controllerAndMethod[2];
//
//        try {
//            Class controllerClass = Class.forName(controllerName);
//            Object controller = injector.getInstance(controllerClass);
//            ModelAndView mv = (ModelAndView)controller.getClass().getMethod(methodName).invoke(controller);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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

