package org.rest_api.pet.Server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDto {

    @Min(value=-100, message = "The minimum air temperature cannot be lower than -100")
    @Max(value=100, message = "The maximum air temperature cannot be higher than 100")
    private float value;

   // @NotEmpty(message = "Rain registration cannot be omitted")
    private boolean raining;


    @NotNull(message = "Measurements cannot contain sensor data")
    @JsonProperty("sensor")
    private SensorDto sensorDto;

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

    public @NotNull(message = "Measurements cannot contain sensor data") SensorDto getSensorDto() {
        return sensorDto;
    }

    public void setSensorDto(@NotNull(message = "Measurements cannot contain sensor data") SensorDto sensorDto) {
        this.sensorDto = sensorDto;
    }
}
