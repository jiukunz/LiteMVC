package com.thoughtworks.unit.models;

import javax.tools.JavaFileManager;

public class Book {
    private String name;
    private Author author;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Book() {

    }

    public Book(String name, Author author) {
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
