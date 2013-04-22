package com.thoughtworks;

import com.thoughtworks.module.IModule;

public class FakeGuice {
	public static Injector createInjector(IModule module) {
		module.config();
		return new Injector(module);
	}
}