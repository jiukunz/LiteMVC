package com.thoughtworks;

import com.google.common.base.Predicate;
import com.sun.xml.internal.ws.util.StringUtils;
import com.thoughtworks.annotation.Inject;
import com.thoughtworks.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.google.common.collect.Iterables.find;

public class ActionHandler {

    private Injector injector;
    private ICustomerConfig config;

    public ActionHandler() {}

    public void setInjector(Injector injector){
        this.injector = injector;
    }

    @Inject
    public void setConfig(ICustomerConfig config) {
        this.config = config;
        config.config();
    }

    public ModelAndView resolve(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();
        Class controllerClass = getControllerClassFromPath(path);
        Object controller = injector.getInstance(controllerClass);
        final String actionName = getActionNameFromPath(path);

        Method action = find(Arrays.asList(controllerClass.getMethods()), new Predicate<Method>() {
            @Override
            public boolean apply(Method method) {
                return method.getName().equals(actionName);
            }
        });

        Object paramModel = new ModelParser(action.getParameterTypes()[0]).parse(request);

        return (ModelAndView) action.invoke(controller, paramModel);
    }

    private String getActionNameFromPath(String path) {
        return path.split("/")[2];
    }

    private Class getControllerClassFromPath(String path) throws ClassNotFoundException {
        String controllerName = StringUtils.capitalize(path.split("/")[1]) + "Controller";
        String className = config.getPackageName() + "." + controllerName;
        return Class.forName(className);
    }
}
