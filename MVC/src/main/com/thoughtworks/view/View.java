package com.thoughtworks.view;

import com.thoughtworks.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class View {
    private String viewName;

    protected View(String viewName) {
        this.viewName = viewName;
    }

    public abstract void render(ModelAndView modelAndView,
                                HttpServletRequest request,
                                HttpServletResponse response
    );
}