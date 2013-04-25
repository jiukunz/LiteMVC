package com.thoughtworks;

import com.thoughtworks.utils.ClassUtils;
import com.thoughtworks.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class ModelParser {
    private Class objectClass;

    public ModelParser(Class objectClass) {
        this.objectClass = objectClass;
    }

    public Object parse(HttpServletRequest request) throws Exception {
        Object model = objectClass.newInstance();
        String prefix = getClazzName(objectClass);

        doParse(request, prefix, model);

        return model;
    }

    private void doParse(HttpServletRequest request, String prefix, Object paramObject) throws Exception {
        for(Field field : paramObject.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(ClassUtils.isPrimitive(field.getType())) {
                String paramStringValue = request.getParameter(prefix + "." + field.getName());
                Object paramValue = StringUtils.toPrimitive(field.getType(), paramStringValue);
                field.set(paramObject, paramValue);
            } else {
                Object nestedParam = field.getType().newInstance();
                field.set(paramObject, nestedParam);
                doParse(request, prefix + "." + field.getName(), nestedParam);
            }
        }
    }

    private String getClazzName(Class clazz) {
        String[] classNames = clazz.getName().toLowerCase().split("\\.");
        return classNames[classNames.length - 1];
    }

}
