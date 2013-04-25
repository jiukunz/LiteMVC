package com.thoughtworks;

import com.thoughtworks.model.ModelAndView;
import com.thoughtworks.module.IModule;
import com.thoughtworks.view.ViewResolver;

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
        ModelAndView modelAndView = null;
        try {
            Injector injector = FakeGuice.createInjector(module);
            ActionHandler actionHandler = (ActionHandler) injector.getInstance(ActionHandler.class);
            actionHandler.setInjector(injector);
            modelAndView = actionHandler.resolve(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewResolver.render(modelAndView,request,response);
    }
}
