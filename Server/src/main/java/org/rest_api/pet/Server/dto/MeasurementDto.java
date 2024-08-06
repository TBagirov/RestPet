package org.rest_api.pet.Server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDto {

    @Min(value=-100, message = "The minimum air temperature cannot be lower than -100")
    @Max(value=100, message = "The maximum air temperature cannot be higher than 100")
    private float value;

    @NotNull(message = "Rain registration cannot be omitted")
    private Boolean raining;

    @NotNull(message = "Measurements cannot contain sensor data")
    // указываем именование ключа в json для этого поля
    @JsonProperty("sensor")
    private SensorDto sensorDto;

    public float getValue() {
        return value;
    }
    public void setValue( float value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDto getSensorDto() {
        return sensorDto;
    }

    public void setSensorDto(SensorDto sensorDto) {
        this.sensorDto = sensorDto;
    }
}
