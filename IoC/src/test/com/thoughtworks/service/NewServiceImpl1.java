package com.thoughtworks.service;

import com.thoughtworks.annotation.Inject;
import com.thoughtworks.annotation.Named;

public class NewServiceImpl1 implements NewService {
    private String name;

	@Override
	public void doSomethingNew() {
        System.out.println(name);
		System.out.println("I'm doing something new!");
	}

    @Inject
    public NewServiceImpl1(@Named(value="New Service Impl1") String name) {
        this.name = name;
    }
}
