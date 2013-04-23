package com.thoughtworks;

import com.sun.xml.internal.ws.util.StringUtils;
import com.thoughtworks.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ActionHandler {

    private final String packageName;
    private Injector injector;

    public ActionHandler(String packageName, Injector injector) {
        this.packageName = packageName;
        this.injector = injector;
    }

    public ModelAndView resolve(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();
        String className = getControllerClassFromPath(path);
        Class controllerClass = Class.forName(className);
        Object controller = injector.getInstance(controllerClass);
        String actionName = getActionNameFromPath(path);
        Method action = controllerClass.getMethod(actionName, HttpServletRequest.class, HttpServletResponse.class);
        ModelAndView mv = (ModelAndView) action.invoke(controller, request, response);
        return mv;
    }

    private String getActionNameFromPath(String path) {
        return path.split("/")[2];
    }

    private String getControllerClassFromPath(String path) {
        String[] controllerAndMethod = path.split("/");
        String controllerName = StringUtils.capitalize(controllerAndMethod[1]) + "Controller";
        return packageName + "." + controllerName;
    }
}
