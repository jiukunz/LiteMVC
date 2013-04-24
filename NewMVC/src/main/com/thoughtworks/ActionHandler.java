package com.thoughtworks;

import com.google.common.base.Predicate;
import com.sun.xml.internal.ws.util.StringUtils;
import com.thoughtworks.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.google.common.collect.Iterables.find;

public class ActionHandler {

    private final String packageName;
    private Injector injector;

    public ActionHandler(String packageName, Injector injector) {
        this.packageName = packageName;
        this.injector = injector;
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

        Object paramModel = new ModelParser(action.getParameterTypes()[0], "com.thoughtworks.model.").parse(request.getParameterMap());

        ModelAndView mv = (ModelAndView) action.invoke(controller, paramModel);
        return mv;
    }

    private String getActionNameFromPath(String path) {
        return path.split("/")[2];
    }

    private Class getControllerClassFromPath(String path) throws ClassNotFoundException {
        String controllerName = StringUtils.capitalize(path.split("/")[1]) + "Controller";
        String className = packageName + "." + controllerName;
        return Class.forName(className);
    }
}
