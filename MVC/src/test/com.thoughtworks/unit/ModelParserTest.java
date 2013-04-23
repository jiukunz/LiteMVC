package com.thoughtworks.unit;

import com.thoughtworks.ModelParser;
import com.thoughtworks.unit.models.Book;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ModelParserTest {

    @Test
    public void should_parse_model_from_params() throws Exception {

        ModelParser parser = new ModelParser(Book.class, "com.thoughtworks.unit.models.");

        Map<String, String[]> params = new HashMap<String, String[]>();
        params.put("book.name", new String[]{"Java"});
        params.put("book.price", new String[]{"33.2"});
        params.put("book.author.name", new String[]{"Jack"});
        params.put("book.author.address.location", new String[]{"1st, Street, Richmond"});
        params.put("book.author.age", new String[]{"38"});

        Book book = (Book) parser.parse(params).get("book");

        assertThat(book.getName(), is("Java"));
        assertThat(book.getPrice(), is(33.2));
        assertThat(book.getAuthor().getName(), is("Jack"));
        assertThat(book.getAuthor().getAddress().getLocation(), is("1st, Street, Richmond"));
        assertThat(book.getAuthor().getAge(), is(38));

    }


}
