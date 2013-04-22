package com.thoughtworks;

import com.thoughtworks.annotation.Inject;
import com.thoughtworks.annotation.Named;
import com.thoughtworks.service.NewService;
import com.thoughtworks.service.Service;

public class Controller {
	private Service service;
	private NewService newService;
    private String serviceName;

    public Controller() {
    }

    public Controller(Service service, NewService newService, @Named(value = "Controller Service") String serviceName) {
		this.service = service;
		this.newService = newService;
        this.serviceName = serviceName;
	}
	
	public void doSomething() {
		service.doSomething();
		newService.doSomethingNew();
        System.out.println(serviceName);
	}

    @Inject
    public void setService(Service service) {
        this.service = service;
    }

    @Inject
    public void setNewService(NewService newService) {
        this.newService = newService;
    }

    @Inject
    public void setServiceName(@Named(value = "hello world")String serviceName) {
        this.serviceName = serviceName;
    }
}