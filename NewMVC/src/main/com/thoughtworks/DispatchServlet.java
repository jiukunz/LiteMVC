package com.thoughtworks;

import com.thoughtworks.module.IModule;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchServlet extends HttpServlet {
    private IModule module;
    private ViewResolver viewResolver;

    public DispatchServlet(IModule module, ViewResolver viewResolver) {
        this.module = module;
        this.viewResolver = viewResolver;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
