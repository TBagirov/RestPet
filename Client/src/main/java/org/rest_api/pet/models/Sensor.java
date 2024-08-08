package org.rest_api.pet.models;

public class Sensor {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sensor(){}
    public Sensor(String name) {
        this.name = name;
    }
}
