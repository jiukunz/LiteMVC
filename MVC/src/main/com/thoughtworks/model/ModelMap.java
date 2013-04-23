package com.thoughtworks.model;

import java.util.HashMap;
import java.util.Map;

public class ModelMap {

	private Map<String, Object> modelMap;
	
	public ModelMap() {
		this.modelMap = new HashMap<String, Object>();
	}

    public ModelMap(Map<String, Object> modelMap) {
        this.modelMap = modelMap;
    }
	public void addModel(String name, Object model) {
		 this.modelMap.put(name,model);
	}

	public Object getModel(String name) {
		return this.modelMap.get(name);
	}

}
