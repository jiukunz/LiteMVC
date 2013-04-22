package com.thoughtworks;

import com.thoughtworks.annotation.Inject;
import com.thoughtworks.annotation.Named;

public class HelloController {

    private String serviceName;

    public String getView() {
        return "hello.jsp";
    }

    public ModelAndView show() {
        ModelAndView mv = new ModelAndView();
        mv.addView("hello.jsp");
        return mv;
    }

    public HelloController() {
    }

    public HelloController(@Named(value = "Controller Service") String serviceName) {
        this.serviceName = serviceName;
    }

    public void doSomething() {
        System.out.println(serviceName);
    }

    @Inject
    public void setServiceName(@Named(value = "hello world")String serviceName) {
        this.serviceName = serviceName;
    }
}
