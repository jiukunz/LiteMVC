package com.thoughtworks;

import com.thoughtworks.models.Book;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ModelParserTest {

    @Test
    public void should_parse_object_from_parameters() throws Exception {

        ModelParser parser = new ModelParser(Book.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        Map<String, String[]> params = new HashMap<String, String[]>();
        params.put("book.name", new String[]{"Java"});
        params.put("book.price", new String[]{"33.2"});
        params.put("book.author.name", new String[]{"Jack"});
        params.put("book.author.age", new String[]{"38"});

        when(request.getParameter("book.name")).thenReturn("Java");
        when(request.getParameter("book.price")).thenReturn("33.2");
        when(request.getParameter("book.author.name")).thenReturn("Jack");
        when(request.getParameter("book.author.age")).thenReturn("38");

        Book book = (Book) parser.parse(request);

        assertThat(book.getName(), is("Java"));
        assertThat(book.getPrice(), is(33.2));
        assertThat(book.getAuthor().getName(), is("Jack"));
        assertThat(book.getAuthor().getAge(), is(38));

    }
}
