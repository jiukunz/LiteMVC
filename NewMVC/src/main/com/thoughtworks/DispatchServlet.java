package com.thoughtworks;

import com.thoughtworks.model.ModelAndView;
import com.thoughtworks.model.ModelMap;
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
        //path example: http://localhost:8080/controller/service
//        Injector injector = FakeGuice.createInjector(module);

        //Controller controller = (Controller)inject.createInstance(Controller.class);//need package of controller

        //process request parameters using binder, and generate the model which is the parameter of action
        //ModelAndView modelAndView = controller.invoke(action).withparameter(model)

        //viewResolver.render(modelAndView)

//        ModelMap modelMap = new ModelMap();
//        modelMap.addModel("result","hello world");
        ModelMap modelMap = null;
        try {
            modelMap = new ActionHandler("com.thoughtworks.controller", FakeGuice.createInjector(module)).resolve(request, response).getModelMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView(modelMap,"canYouRun.ftl");
        viewResolver.render(modelAndView,request,response);
    }
}
