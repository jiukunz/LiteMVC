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
    public void should_parse_model_from_params() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ModelParser.setModelTypes(Note.class, "com.thoughtworks.unit.models.");

        HashMap<String, String[]> params = new HashMap<String, String[]>();
        params.put("note.name", new String[]{"Java"});
        params.put("note.author.name", new String[]{"Jack"});
        params.put("note.author.address.location", new String[]{"1st, Street, Richmond"});

        Note note = (Note) ModelParser.parse(params, "com.thoughtworks.unit.models.").get("note");

        assertThat(note.getName(), is("Java"));
        assertThat(note.getAuthor().getName(), is("Jack"));
        assertThat(note.getAuthor().getAddress().getLocation(), is("1st, Street, Richmond"));

    }


}
