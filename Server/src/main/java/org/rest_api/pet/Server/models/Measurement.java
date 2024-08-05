package org.rest_api.pet.Server.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name="Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="value")
    @Min(value=-100, message = "The minimum air temperature cannot be lower than -100")
    @Max(value=100, message = "The maximum air temperature cannot be higher than 100")
    private float value;

    @Column(name="raining")
    // @NotEmpty(message = "Rain registration cannot be omitted")
    private boolean raining;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    @NotNull(message = "Measurements cannot contain sensor data")
    private Sensor sensor;

    public Measurement() {}

    public Measurement(float value, boolean raining, Sensor sensor, LocalDateTime createdAt) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Min(value = -100, message = "The minimum air temperature cannot be lower than -100")
    @Max(value = 100, message = "The maximum air temperature cannot be higher than 100")
    public float getValue() {
        return value;
    }

    public void setValue(@Min(value = -100, message = "The minimum air temperature cannot be lower than -100") @Max(value = 100, message = "The maximum air temperature cannot be higher than 100") float value) {
        this.value = value;
    }

    //@NotEmpty(message = "Rain registration cannot be omitted")
    public boolean isRaining() {
        return raining;
    }

    public void setRaining(/*@NotEmpty(message = "Rain registration cannot be omitted")*/ boolean raining) {
        this.raining = raining;
    }

    public @NotNull(message = "Measurements cannot contain sensor data") Sensor getSensor() {
        return sensor;
    }

    public void setSensor(@NotNull(message = "Measurements cannot contain sensor data") Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
