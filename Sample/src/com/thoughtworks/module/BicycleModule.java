package com.thoughtworks.module;

import com.thoughtworks.ICustomerConfig;
import com.thoughtworks.service.RunService;
import com.thoughtworks.service.RunServiceImpl;
import com.thoughtworks.servlet.BicycleConfig;

public class BicycleModule extends AbstractModule {
    @Override
    public void config() {
        createMapping(RunService.class, RunServiceImpl.class);
        createMapping(ICustomerConfig.class,BicycleConfig.class);
    }
}
