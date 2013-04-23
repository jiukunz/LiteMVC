package com.thoughtworks.model;

import com.thoughtworks.view.View;

public class ModelAndView {
	private ModelMap modelMap;
	private View view;

	public ModelAndView(ModelMap modelMap, View view) {
		this.modelMap = modelMap;
		this.view = view;
	}

    public ModelMap getModelMap() {
        return modelMap;
    }

    public void setModelMap(ModelMap modelMap) {
        this.modelMap = modelMap;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
