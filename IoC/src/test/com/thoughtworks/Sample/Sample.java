package com.thoughtworks.Sample;

import com.thoughtworks.Controller;
import com.thoughtworks.module.Module;
import com.thoughtworks.FakeGuice;
import com.thoughtworks.Injector;

public class Sample {
	public static void main(String[] args) throws Exception {
		Injector injector = FakeGuice.createInjector(new Module());
		
		Controller controller = (Controller) injector.getInstance(Controller.class);
		
		controller.doSomething();
	}
}
