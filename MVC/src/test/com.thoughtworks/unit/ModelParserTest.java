package com.thoughtworks.unit;

import com.thoughtworks.ModelParser;
import com.thoughtworks.unit.models.Note;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ModelParserTest {

    @Test
    public void should_parse_model_from_params() throws Exception {

        ModelParser parser = new ModelParser(Note.class, "com.thoughtworks.unit.models.");

        HashMap<String, String[]> params = new HashMap<String, String[]>();
        params.put("note.name", new String[]{"Java"});
        params.put("note.price", new String[]{"33.2"});
        params.put("note.author.name", new String[]{"Jack"});
        params.put("note.author.address.location", new String[]{"1st, Street, Richmond"});
        params.put("note.author.age", new String[]{"38"});

        Note note = (Note) parser.parse(params).get("note");

        assertThat(note.getName(), is("Java"));
        assertThat(note.getPrice(), is(33.2));
        assertThat(note.getAuthor().getName(), is("Jack"));
        assertThat(note.getAuthor().getAddress().getLocation(), is("1st, Street, Richmond"));
        assertThat(note.getAuthor().getAge(), is(38));

    }


}
