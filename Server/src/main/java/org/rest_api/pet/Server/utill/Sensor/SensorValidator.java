package org.rest_api.pet.Server.utill.Sensor;

import org.rest_api.pet.Server.models.Sensor;
import org.rest_api.pet.Server.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private SensorService service;

    @Autowired
    public SensorValidator(SensorService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(service.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name","",
                    "This name is already in use");

    }
}
