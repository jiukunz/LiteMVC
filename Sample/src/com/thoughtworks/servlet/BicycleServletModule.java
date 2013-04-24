package com.thoughtworks.servlet;

import com.thoughtworks.FreemarkerResolver;
import com.thoughtworks.ViewResolver;
import com.thoughtworks.module.AbstractModule;
import com.thoughtworks.module.BicycleModule;
import com.thoughtworks.module.IModule;
import com.thoughtworks.view.FreemarkerConfiguration;
import freemarker.template.Configuration;

public class BicycleServletModule extends AbstractModule {
    @Override
    public void config() {
        createMapping(IModule.class,BicycleModule.class);
        createMapping(ViewResolver.class,FreemarkerResolver.class);
        createMapping(Configuration.class,FreemarkerConfiguration.class);
    }
}
