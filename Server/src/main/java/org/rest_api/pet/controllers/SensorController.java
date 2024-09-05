package org.rest_api.pet.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.rest_api.pet.dto.SensorDto;
import org.rest_api.pet.models.Sensor;
import org.rest_api.pet.services.SensorService;
import org.rest_api.pet.utill.Sensor.SensorErrorResponse;
import org.rest_api.pet.utill.Sensor.SensorNotCreatedException;
import org.rest_api.pet.utill.Sensor.SensorValidator;
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
@RequestMapping("/sensors")
@Tag(name = "Sensor Controller", description = "Контроллер для взаимодействия с сенсором")
public class SensorController {
    private final SensorService sensorService;
    SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    @Operation(
            summary = "Регистрация сенсора",
            description = "Регистрация сенсора с указанным наименованием"
    )
    public ResponseEntity<HttpStatus> registerSensor(
            @RequestBody @Valid @Parameter(description = "Наименование добавляемого сенсора", required = true) SensorDto sensorDto,
            BindingResult bindingResult) {

        Sensor sensor = convertToSensor(sensorDto);
        sensorValidator.validate(sensor, bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                errMsg.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            throw new SensorNotCreatedException(errMsg.toString());
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    @Operation(
            summary = "Получение всех сенсоров",
            description = "Получение данных о всех сенсорах имеющихся в БД"
    )
    public List<SensorDto> getSensors(){
        return sensorService.findAll().stream()
                .map(this::convertToSensorDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение сенсора по id",
            description = "Получение данных о конекртном сенсоре по указанному id"
    )
    public SensorDto getSensor(
            @PathVariable("id") @Parameter(description = "id сенсора чьи данные хотим получить") int id
    ){
        return convertToSensorDto(sensorService.findOne(id));
    }

    public Sensor convertToSensor(SensorDto sensorDto){
        return modelMapper.map(sensorDto, Sensor.class);
    }

    public SensorDto convertToSensorDto(Sensor sensor){
        return modelMapper.map(sensor, SensorDto.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException ex){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
