package org.rest_api.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(
        description = "включает в себя наименование сенсора"
)
public class SensorDto {
    @NotEmpty
    @Size(min=3, max=30, message = "The sensor name can contain from 3 to 30 characters")
    @Schema(description = "Наименование сенсора", example = "Sensor1")
    private String name;

}
