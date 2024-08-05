package org.rest_api.pet.Server.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDto {
    @NotEmpty
    @Size(min=3, max=30, message = "The sensor name can contain from 3 to 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
