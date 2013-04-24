package com.thoughtworks.model;

public class ModelAndView {
	private ModelMap modelMap;
	private String viewName;

	public ModelAndView(ModelMap modelMap, String viewName) {
		this.modelMap = modelMap;
		this.viewName = viewName;
	}

    public ModelMap getModelMap() {
        return modelMap;
    }

    public void setModelMap(ModelMap modelMap) {
        this.modelMap = modelMap;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
