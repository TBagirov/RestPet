package org.rest_api.pet.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.rest_api.pet.models.Measurement;
import org.rest_api.pet.models.Sensor;
import org.rest_api.pet.responses.MeasurementErrorResponse;
import org.rest_api.pet.responses.MeasurementList;
import org.rest_api.pet.responses.SensorErrorResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class App {

    private RestTemplate restTemplate = new RestTemplate();

    public App(){}

    public String addSensor(String name) {
        String url = "http://localhost:8080/sensors/registration";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Sensor body = new Sensor(name);
        HttpEntity<Sensor> request = new HttpEntity<>(body, headers);
        String str = "";

        try {
            str = restTemplate.postForObject(url, request, String.class);
        } catch (HttpClientErrorException e) {
            String error = e.getResponseBodyAsString();

            ObjectMapper objectMapper = new ObjectMapper();
            SensorErrorResponse errorResponse = new SensorErrorResponse();
            try{
                errorResponse = objectMapper.readValue(error, SensorErrorResponse.class);
            } catch (Exception ex) {
                System.out.println("Ошибка десериализации в SensorErrorResponse");
                ex.printStackTrace();
            }

            System.out.println("\n----- Ошибка -----");
            System.out.println(errorResponse.getMessage());
            System.out.println(errorResponse.getTimestamp());
        }

        return str;
    }

    public  String addMeasurement(Measurement measurement){
        String url = "http://localhost:8080/measurements/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Measurement body = new Measurement(measurement.getValue(), measurement.getRaining(), measurement.getSensor());
        HttpEntity<Measurement> request = new HttpEntity<>(body, headers);

        String str = "";
        try {
            str = restTemplate.postForObject(url, request, String.class);
        } catch(HttpClientErrorException e){
            String error = e.getResponseBodyAsString();

            ObjectMapper objectMapper = new ObjectMapper();
            MeasurementErrorResponse errorResponse = new MeasurementErrorResponse();
            try{
                errorResponse = objectMapper.readValue(error, MeasurementErrorResponse.class);
            } catch(Exception ex){
                System.out.println("Ошибка десериализации в MeasurementErrorResponse");
                ex.printStackTrace();
            }

            System.out.println("\n----- Ошибка -----");
            System.out.println(errorResponse.getMessage());
            System.out.println(errorResponse.getTimestamp());
        }

        return str;
    }

    public  MeasurementList getMeasurements(){
        String url = "http://localhost:8080/measurements";

        // из-за сложной десериализации
        ResponseEntity<List<Measurement>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Measurement>>(){}
        );

        return new MeasurementList(responseEntity.getBody());
    }

    public  Integer getRainyDaysCount(){
        String url = "http://localhost:8080/measurements/rainyDaysCount";

        return restTemplate.getForObject(url, Integer.class);
    }


}
