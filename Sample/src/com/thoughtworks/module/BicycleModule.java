package com.thoughtworks.module;

import com.thoughtworks.model.Bicycle;
import com.thoughtworks.service.RunService;

public class BicycleModule extends AbstractModule {
    @Override
    public void config() {
        createMapping(RunService.class, Bicycle.class);
    }
}
