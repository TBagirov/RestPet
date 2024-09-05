package org.rest_api.pet.utill.Measurement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class MeasurementErrorResponse {


    private String message;
    private LocalDateTime timestamp;

}
