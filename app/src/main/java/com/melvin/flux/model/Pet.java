package com.melvin.flux.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "Pet", strict = false)
public class Pet {

    @Element(name = "id")
    private String id;

    @Element(name = "name", required = false)
    private String name;


    public Pet() {
    }

    public Pet(String id, String name) {
        this.id = id;
        this.name = name;
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
}
