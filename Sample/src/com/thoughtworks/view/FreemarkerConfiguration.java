package com.thoughtworks.view;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;

public class FreemarkerConfiguration extends Configuration {
    public FreemarkerConfiguration() {
        try {
            setDirectoryForTemplateLoading(new File("./Sample/src/resource"));
        } catch (IOException e) {
            System.out.println("Cant find the templates directory.");
        }
    }
}
