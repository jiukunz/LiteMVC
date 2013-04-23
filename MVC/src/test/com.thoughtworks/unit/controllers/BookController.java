package com.thoughtworks.unit.controllers;

import com.sun.tools.internal.xjc.ModelLoader;
import com.thoughtworks.ModelParser;
import com.thoughtworks.annotation.Inject;
import com.thoughtworks.annotation.Model;
import com.thoughtworks.annotation.Named;
import com.thoughtworks.model.ModelAndView;
import com.thoughtworks.model.ModelMap;
import com.thoughtworks.unit.models.Address;
import com.thoughtworks.unit.models.Author;
import com.thoughtworks.unit.models.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Model(modelType = Book.class)
public class BookController {

    private String serviceName;

    public BookController() {
    }

    public BookController(@Named(value = "Book Service")String serviceName) {

        this.serviceName = serviceName;

    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView(new ModelMap(), "show.ftl");
        mv.getModelMap().addModel("Book", new Book("Java",new Author("Jack", new Address("abc"), 34)));
        return mv;
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView(new ModelMap(), "create.ftl");
        ModelParser parser = new ModelParser(Book.class, "com.thoughtworks.unit.models.");
        Book book = (Book) parser.parse(request.getParameterMap()).get("book");
        mv.getModelMap().addModel("Book",book);
        return mv;
    }

}
