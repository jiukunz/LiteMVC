package com.thoughtworks.servlet;

import com.thoughtworks.DispatchServlet;
import com.thoughtworks.ViewResolver;
import com.thoughtworks.annotation.Inject;
import com.thoughtworks.module.IModule;

public class BicycleServlet extends DispatchServlet {
    @Inject
    public BicycleServlet(IModule module, ViewResolver viewResolver) {
        super(module, viewResolver);
    }
}
