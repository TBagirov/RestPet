package org.rest_api.pet.Server.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.rest_api.pet.Server.dto.MeasurementDto;
import org.rest_api.pet.Server.dto.SensorDto;
import org.rest_api.pet.Server.models.Measurement;
import org.rest_api.pet.Server.models.Sensor;
import org.rest_api.pet.Server.services.MeasurementService;
import org.rest_api.pet.Server.utill.Measurement.MeasurementErrorResponse;
import org.rest_api.pet.Server.utill.Measurement.MeasurementNotCreatedException;
import org.rest_api.pet.Server.utill.Measurement.MeasurementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private MeasurementService measurementService;
    MeasurementValidator measurementValidator;
    private ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 MeasurementValidator measurementValidator,
                                 ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDto measurementDto,
                                                     BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDto);

        measurementValidator.validate(measurement, bindingResult);

        if(bindingResult.hasErrors()){
            StringBuilder errMsg = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                errMsg.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            throw new MeasurementNotCreatedException(errMsg.toString());
        }

        measurementService.save(measurement);
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
        Measurement measurement = modelMapper.map(measurementDto, Measurement.class);
        Sensor sensor = modelMapper.map(measurementDto.getSensorDto(), Sensor.class);

        measurement.setSensor(sensor);

        return measurement;
    }

    public MeasurementDto convertToMeasurementDto(Measurement measurement){
        MeasurementDto measurementDto = modelMapper.map(measurement, MeasurementDto.class);
        SensorDto sensorDto = modelMapper.map(measurement.getSensor(), SensorDto.class);

        measurementDto.setSensorDto(sensorDto);

        return measurementDto;
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> exceptionHandler(MeasurementNotCreatedException ex){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
            ex.getMessage(),
            LocalDateTime.now()
        );

        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
