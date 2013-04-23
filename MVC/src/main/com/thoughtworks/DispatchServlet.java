package com.thoughtworks;

import com.thoughtworks.view.FreemarkerViewResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DispatchServlet extends HttpServlet {

    private Injector injector;
    private FreemarkerViewResolver viewResolver;

    public DispatchServlet(Injector injector, FreemarkerViewResolver viewResolver) {
        this.injector = injector;
        this.viewResolver = viewResolver;
    }

    @Override
    public void init() throws ServletException {
        try {
            viewResolver.getConfig().setDirectoryForTemplateLoading(new File(viewResolver.getViewPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          viewResolver.render(null,request,response);
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

