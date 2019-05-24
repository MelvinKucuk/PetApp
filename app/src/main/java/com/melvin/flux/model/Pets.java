package com.melvin.flux.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "pets", strict = false)
public class Pets {

    @ElementList(inline = true, name = "Pet")
    private List<Pet> pets;

    public Pets() {
    }

    public Pets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }
}
