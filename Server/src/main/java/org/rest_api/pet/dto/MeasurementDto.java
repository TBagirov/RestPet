package org.rest_api.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(
        description = "Сущность измерения сенсора"
)
public class MeasurementDto {

    @Min(value=-100, message = "The minimum air temperature cannot be lower than -100")
    @Max(value=100, message = "The maximum air temperature cannot be higher than 100")
    @Schema(description="Температура", example = "33.3", requiredMode = Schema.RequiredMode.REQUIRED)
    private float value;

    @NotNull(message = "Rain registration cannot be omitted")
    @Schema(description = "Зарегистрировал ли сенсор дождь", example = "true")
    private Boolean raining;

    @NotNull(message = "Measurements cannot contain sensor data")
    // указываем именование ключа в json для этого поля
    @JsonProperty("sensor")
    @Schema(description = "Наименование сенсора", example = "Sensor1")
    private SensorDto sensorDto;


}
