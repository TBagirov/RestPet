package org.rest_api.pet.Server.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.rest_api.pet.Server.ServerApplication;
import org.rest_api.pet.Server.dto.MeasurementDto;
import org.rest_api.pet.Server.dto.SensorDto;
import org.rest_api.pet.Server.models.Measurement;
import org.rest_api.pet.Server.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


//   ---------- !!! НЕ ТЕСТИЛОСЬ !!! ----------
@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private MeasurementService measurementService;
    private ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDto measurementDto) {

        // TODO: сенсору при добавлении надо как-то вставлять id
       // Measurement measurement = convertToMeasurement(measurementDto);
        measurementService.save(convertToMeasurement(measurementDto));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDto> getAllMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.findByRainyDaysCount().size();
    }

    public Measurement convertToMeasurement(MeasurementDto measurementDto){
        return modelMapper.map(measurementDto, Measurement.class);

    }

    public MeasurementDto convertToMeasurementDto(Measurement measurement){
        MeasurementDto measurenentDto = modelMapper.map(measurement, MeasurementDto.class);
        SensorDto sensorDto = modelMapper.map(measurement.getSensor(), SensorDto.class);

        measurenentDto.setSensorDto(sensorDto);

        return measurenentDto;
    }
}
