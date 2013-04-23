package com.thoughtworks.unit.models;

public class Author {
    private String name;
    private Address address;
    private Integer age;

    public Author() {

    }

    public Author(String name, Address address, Integer age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
