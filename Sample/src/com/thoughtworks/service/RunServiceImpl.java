package com.thoughtworks.service;

import com.thoughtworks.model.Bicycle;

public class RunServiceImpl implements RunService {

    @Override
    public String run(Bicycle bicycle) {
        return bicycle.shout();
    }
}
