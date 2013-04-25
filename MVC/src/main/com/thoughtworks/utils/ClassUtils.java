package com.thoughtworks.utils;

import java.util.HashSet;

public class ClassUtils {
    private static final HashSet<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isPrimitive(Class<?> clazz)
    {
        return clazz.isPrimitive() || WRAPPER_TYPES.contains(clazz);
    }

    private static HashSet<Class<?>> getWrapperTypes()
    {
        HashSet<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(String.class);
        ret.add(Void.class);
        return ret;
    }
}
