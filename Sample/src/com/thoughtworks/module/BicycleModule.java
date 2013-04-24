package com.thoughtworks.module;

import com.thoughtworks.service.RunService;
import com.thoughtworks.service.RunServiceImpl;

public class BicycleModule extends AbstractModule {
    @Override
    public void config() {
        createMapping(RunService.class, RunServiceImpl.class);
    }
}
