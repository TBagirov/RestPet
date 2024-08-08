package org.rest_api.pet.models;

public class Measurement {
    private float value;
    private boolean raining;
    private Sensor sensor;

    public Measurement() {}
    public Measurement(float value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return String.format("value=%7.2f | raining=%-5b | sensor=%-15s", value, raining, sensor.getName());
    }
}
