package com.thoughtworks;

import main.com.thoughtworks.annotation.Named;
import main.com.thoughtworks.mvc.model.ModelAndView;

public class HomeController {

    public ModelAndView show() {
        ModelAndView mv = new ModelAndView();
        mv.addView("home.jsp");
        return mv;
    }

    private String serviceName;

    public HomeController() {
    }

//    public HomeController(@Named(value = "Controller Service") String serviceName) {
//        this.serviceName = serviceName;
//    }

    public void doSomething() {
        System.out.println(serviceName);
    }

//    @Inject
    public void setServiceName(@Named(value = "hello world")String serviceName) {
        this.serviceName = serviceName;
    }
}
