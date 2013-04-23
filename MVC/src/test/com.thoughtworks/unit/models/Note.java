package com.thoughtworks.unit.models;

import javax.tools.JavaFileManager;

public class Note {
    private String name;
    private Author author;

    public Note() {

    }

    public Note(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
