package com.thoughtworks.view;

import com.thoughtworks.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewResolver {
    void render(ModelAndView modelAndView,
                HttpServletRequest request,
                HttpServletResponse response
    );
}
