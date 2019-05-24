package com.melvin.flux.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "category", strict = false)
public class Category {

    @Element(name = "name", required = false)
    private String name;

    public Category() {
    }

    public String getName() {
        return name;
    }
}
