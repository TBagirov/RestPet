package org.rest_api.pet.Server.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.rest_api.pet.Server.models.Measurement;
import org.rest_api.pet.Server.models.Sensor;

import java.util.List;

public class MeasurementDto {

    @Min(value=-100, message = "The minimum air temperature cannot be lower than -100")
    @Min(value=100, message = "The maximum air temperature cannot be higher than 100")
    private float value;

   // @NotEmpty(message = "Rain registration cannot be omitted")
    private boolean raining;


    @NotNull(message = "Measurements cannot contain sensor data")
    private Sensor sensor;

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
}
