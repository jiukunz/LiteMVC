package com.thoughtworks.view;

import com.thoughtworks.model.ModelAndView;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FreemarkerViewResolver implements ViewResolver {
    private final String viewPath;
    private Configuration config = new Configuration();

    public FreemarkerViewResolver(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public void render(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        Map root = newHashMap();
        root.put("message", "Hello World!");
        try {
            Template template = config.getTemplate("test/test.ftl");
            response.setContentType("text/html; charset=" + template.getEncoding());
            Writer out = response.getWriter();
            template.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TemplateException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public String getViewPath() {
        return viewPath;
    }
}
