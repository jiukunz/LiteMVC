package com.thoughtworks.module;

import com.thoughtworks.service.NewService;
import com.thoughtworks.service.NewServiceImpl1;
import com.thoughtworks.service.Service;
import com.thoughtworks.service.ServiceImpl2;

public class Module extends AbstractModule {
	public void config() {
		createMapping(Service.class, ServiceImpl2.class);
		createMapping(NewService.class, NewServiceImpl1.class);
	}
}