package org.rest_api.pet.utill.Sensor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class SensorErrorResponse {
    private String message;
    private LocalDateTime timestamp;

}
