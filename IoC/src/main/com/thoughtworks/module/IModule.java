package com.thoughtworks.module;

public interface IModule {
    void config();

    <T> Class<? extends T> getMapping(Class<T> type);
}