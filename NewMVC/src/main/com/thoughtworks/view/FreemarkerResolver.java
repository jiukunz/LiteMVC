package com.thoughtworks.view;

import com.thoughtworks.model.ModelAndView;
import com.thoughtworks.model.ModelMap;
import com.thoughtworks.annotation.Inject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class FreemarkerResolver implements ViewResolver {

    private Configuration config;

    @Inject
    public FreemarkerResolver(Configuration config) {
        this.config = config;
    }

    @Override
    public void render(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        ModelMap modelMap = modelAndView.getModelMap();
        Template template = null;
        try {
            template = config.getTemplate(modelAndView.getViewName());
        } catch (IOException e) {
            System.err.println("Can't find template file.");
        }
        response.setContentType("text/html; charset=" + template.getEncoding());
        try {
            Writer out = response.getWriter();
            template.process(modelMap.getModelMap(), out);
        } catch (TemplateException e) {
            System.err.println("Temple process error.");
        } catch (IOException e) {
            System.err.println("Can't open template.");
        }

    }
}
