package com.melvin.flux.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "Pet", strict = false)
public class Pet  {

    @Element(name = "id")
    private String id;

    @Element(name = "name", required = false)
    private String name;

    @Element(name = "category", required = false)
    private Category category;

    public Pet() {
    }

    public Pet(String id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }
}
