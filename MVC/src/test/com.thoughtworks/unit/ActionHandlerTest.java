package com.thoughtworks.unit;

import com.thoughtworks.ActionHandler;
import com.thoughtworks.FakeGuice;
import com.thoughtworks.model.ModelAndView;
import com.thoughtworks.unit.models.Book;
import com.thoughtworks.unit.module.Module;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActionHandlerTest {
    @Test
    public void should_invoke_controller_get_action() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getPathInfo()).thenReturn("/book/show");

        ActionHandler actionHandler = new ActionHandler("com.thoughtworks.unit.controllers", FakeGuice.createInjector(new Module()));

        ModelAndView mv = actionHandler.resolve(request, response);

        assertThat(mv.getViewName(), is("show.ftl"));
        assertThat(((Book)mv.getModelMap().getModel("book")).getName(), is("Java"));
        assertThat(((Book)mv.getModelMap().getModel("book")).getAuthor().getName(), is("Jack"));

    }

    @Test
    public void should_invoke_controller_post_action() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getPathInfo()).thenReturn("/book/create");

        Map<String, String[]> params = new HashMap<String, String[]>();
        params.put("book.name", new String[]{"Java"});
        params.put("book.price", new String[]{"33.2"});
        params.put("book.author.name", new String[]{"Jack"});
        params.put("book.author.address.location", new String[]{"1st, Street, Richmond"});
        params.put("book.author.age", new String[]{"38"});

        when(request.getParameterMap()).thenReturn(params);

        ActionHandler actionHandler = new ActionHandler("com.thoughtworks.unit.controllers", FakeGuice.createInjector(new Module()));

        ModelAndView mv = actionHandler.resolve(request, response);

        assertThat(mv.getViewName(), is("create.ftl"));
        assertThat(((Book)mv.getModelMap().getModel("book")).getName(), is("Java"));
        assertThat(((Book)mv.getModelMap().getModel("book")).getAuthor().getName(), is("Jack"));
        assertThat(((Book)mv.getModelMap().getModel("book")).getAuthor().getAge(), is(38));
        assertThat(((Book)mv.getModelMap().getModel("book")).getAuthor().getAddress().getLocation(), is("1st, Street, Richmond"));

    }
}
