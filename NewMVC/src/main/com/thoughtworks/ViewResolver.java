package com.thoughtworks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewResolver {
    void render(ModelAndView modelAndView,
                HttpServletRequest request,
                HttpServletResponse response
    );
}
