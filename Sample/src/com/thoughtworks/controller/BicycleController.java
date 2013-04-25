package com.thoughtworks.controller;

import com.thoughtworks.annotation.Get;
import com.thoughtworks.annotation.Post;
import com.thoughtworks.model.ModelAndView;
import com.thoughtworks.model.ModelMap;
import com.thoughtworks.annotation.Inject;
import com.thoughtworks.model.Bicycle;
import com.thoughtworks.model.Wheel;
import com.thoughtworks.service.RunService;

public class BicycleController {

    private RunService runService;

    @Inject
    public BicycleController(RunService runService) {
        this.runService = runService;
    }

    @Get
    public ModelAndView show() {
        Bicycle bike = new Bicycle(new Wheel(33), 3);
        String result = runService.run(bike);
        ModelMap modelMap = new ModelMap();
        modelMap.addModel("result",result);
        return new ModelAndView(modelMap,"iCanRun.ftl");
    }

    @Get
    public ModelAndView newBicycle() {
        ModelMap modelMap = new ModelMap();
        return new ModelAndView(modelMap,"new.ftl");
    }

    @Post
    public ModelAndView canYouRun(Bicycle bicycle) {
        String result = runService.run(bicycle);
        ModelMap modelMap = new ModelMap();
        modelMap.addModel("result",result);
        return new ModelAndView(modelMap,"canYouRun.ftl");
    }

    @Post
    public ModelAndView create(Bicycle bicycle) {
        String result = runService.run(bicycle);
        ModelMap modelMap = new ModelMap();
        modelMap.addModel("result",result);
        return new ModelAndView(modelMap,"canYouRun.ftl");
    }
}
