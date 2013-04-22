package com.thoughtworks.module;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

abstract class AbstractModule implements IModule {
    private Map<Class<?>, Class<?>> classMap = newHashMap();

    public abstract void config();

    protected <T> void createMapping(Class<T> baseClass, Class<? extends T> subClass) {
        classMap.put(baseClass, subClass.asSubclass(baseClass));
    }

    public <T> Class<? extends T> getMapping(Class<T> baseClass) {
        Class<?> implementation = classMap.get(baseClass);

        if (implementation == null)
            throw new IllegalArgumentException("Couldn't find the implementation of class " + baseClass);

        return implementation.asSubclass(baseClass);
    }
}
