package com.thoughtworks.servlet;

import com.thoughtworks.CustomerConfig;
import com.thoughtworks.ICustomerConfig;

public class BicycleConfig extends CustomerConfig {

    public void config() {
        setPackageName("com.thoughtworks.controller");
    }

}
