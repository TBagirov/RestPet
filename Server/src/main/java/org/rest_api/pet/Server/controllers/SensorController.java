package org.rest_api.pet.Server.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.rest_api.pet.Server.dto.SensorDto;
import org.rest_api.pet.Server.models.Sensor;
import org.rest_api.pet.Server.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDto sensorDto) {
        sensorService.save(convertToSensor(sensorDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<SensorDto> getSensors(){
        return sensorService.findAll().stream()
                .map(this::convertToSensorDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SensorDto getSensor(@PathVariable("id") int id){
        return convertToSensorDto(sensorService.findOne(id));
    }

    public Sensor convertToSensor(SensorDto sensorDto){
        return modelMapper.map(sensorDto, Sensor.class);
    }

    public SensorDto convertToSensorDto(Sensor sensor){
        return modelMapper.map(sensor, SensorDto.class);
    }
}
