package com.thoughtworks.controller;

import com.thoughtworks.ModelAndView;
import com.thoughtworks.ModelMap;
import com.thoughtworks.annotation.Inject;
import com.thoughtworks.service.RunService;

public class BicycleController {

    private RunService runService;

    @Inject
    public BicycleController(RunService runService) {
        this.runService = runService;
    }

    public ModelAndView canYouRun() {
        String result = runService.run();
        ModelMap modelMap = new ModelMap();
        modelMap.addModel("result",result);
        return new ModelAndView(modelMap,"canYouRun.ftl");
    }
}
