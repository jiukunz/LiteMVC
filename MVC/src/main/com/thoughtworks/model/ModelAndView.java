package com.thoughtworks.model;

import com.thoughtworks.view.ViewResolver;

public class ModelAndView {
	private ModelMap modelMap;
	private ViewResolver viewResolver;

	public ModelAndView(ModelMap modelMap, ViewResolver viewResolver) {
		this.modelMap = modelMap;
		this.viewResolver = viewResolver;
	}

    public ModelMap getModelMap() {
        return modelMap;
    }

    public void setModelMap(ModelMap modelMap) {
        this.modelMap = modelMap;
    }

    public ViewResolver getViewResolver() {
        return viewResolver;
    }

    public void setViewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }
}
