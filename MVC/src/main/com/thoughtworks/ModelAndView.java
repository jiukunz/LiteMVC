package com.thoughtworks;

import java.util.HashMap;

public class ModelAndView {
    private HashMap<String,Object> modelMap = new HashMap<String, Object>();
    private String viewName;

    public void addModel(String name, Object model) {
        modelMap.put(name, model);
    }

    public void addView(String view) {
        viewName = view;
    }

    public HashMap<String, Object> getModelMap() {
        return modelMap;
    }

    public String getViewName() {
        return viewName;
    }
}
