package com.thoughtworks;

import com.thoughtworks.annotation.Inject;
import com.thoughtworks.annotation.Named;
import com.thoughtworks.module.IModule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Injector {
    private IModule module;

    public Injector(IModule module) {
        this.module = module;
    }

    @SuppressWarnings("unchecked")
    public Object getInstance(Class clazz) throws Exception {
        Object instance = null;
        if (injectUsingConstructor(clazz)) {
            for (Constructor constructor : clazz.getConstructors()) {
                if (constructor.isAnnotationPresent(Inject.class)) {
                    Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                    Class[] parameterTypes = constructor.getParameterTypes();
                    List<Object> parameters = getParameters(parameterTypes, parameterAnnotations);
                    instance = constructor.newInstance(parameters.toArray());
                }
            }
        } else {
            instance = clazz.newInstance();
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(Inject.class)) {
                    Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                    Class[] parameterTypes = method.getParameterTypes();
                    List<Object> parameters = getParameters(parameterTypes, parameterAnnotations);
                    method.invoke(instance, parameters.toArray());
                }
            }
        }
        return instance;
    }

    private List<Object> getParameters(Class[] parameterTypes, Annotation[][] parameterAnnotations) throws Exception, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Object> parameters = newArrayList();
        int i = 0;
        for (Annotation[] annotations : parameterAnnotations) {
            Class parameterType = parameterTypes[i++];
            if (annotations.length == 0) {
                Class dependency = module.getMapping(parameterType);
                if (parameterType.isAssignableFrom(dependency)) {
                    parameters.add(getInstance(dependency));
                }
            } else {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Named) {
                        parameters.add(((Named) annotation).value());
                    }
                }
            }
        }
        return parameters;
    }

    private boolean injectUsingConstructor(Class clazz) {
        for (Constructor constructor : clazz.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                return true;
            }
        }
        return false;
    }
}
