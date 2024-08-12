package org.rest_api.pet;

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
import java.util.Locale;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    private static Scanner sc = new Scanner(System.in);
    private static RestTemplate restTemplate = new RestTemplate();

    static {
        sc.useLocale(Locale.UK);

    }

    public static void main( String[] args ) {

        while(true) {
            System.out.println("1. Добавить сенсор");
            System.out.println("2. Добавить измерение сенсора");
            System.out.println("3. Получить измерения");
            System.out.println("4. Получить количество дождливых дней");
            System.out.println("0. Выход");

            System.out.print("Выберите желаемое действие: ");
            int choice = sc.nextInt();

            clearConsole();

            if(choice == 0) {

                System.out.println("Завершение работы, возвращайтесь!");
                break;
            }

            switch(choice) {
                case 1: {
                    System.out.print("Введите название добавляемого сенсора: ");
                    String name = sc.next();
                    System.out.println("\nРезультат выполнения запроса: " +  addSensor(name));

                    waitClickEnter();

                    break;
                }

                case 2: {
                    Measurement measurement = new Measurement();

                    System.out.print("\nВведите температуру воздуха: ");
                    measurement.setValue(sc.nextFloat());

                    System.out.print("Введите зарегестрировал ли сенсор дождь: ");
                    measurement.setRaining(sc.nextBoolean());

                    System.out.print("Введите название сенсора, который провел измерения: ");
                    measurement.setSensor(new Sensor(sc.next()));

                    System.out.println("\nРезультат выполнения запроса: " +  addMeasurement(measurement));

                    waitClickEnter();

                    break;
                }

                case 3: {
                    clearConsole();
                    System.out.println();
                    getMeasurements().getMeasurementList().forEach(System.out::println);

                    System.out.println();
                    waitClickEnter();

                    break;
                }

                case 4:{
                    System.out.println("\nКоличество дождливый дней = " + getRainyDaysCount() + "\n");

                    waitClickEnter();

                    break;
                }
            } // switch

            clearConsole();
        }// while

    }

    public static String addSensor(String name) {
        String url = "http://localhost:8080/sensors/registration";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Sensor   body = new Sensor(name);
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

    public static String addMeasurement(Measurement measurement){
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

    public static MeasurementList getMeasurements(){
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

    public static Integer getRainyDaysCount(){
        String url = "http://localhost:8080/measurements/rainyDaysCount";

        return restTemplate.getForObject(url, Integer.class);
    }

    public static void clearConsole() {
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }

    public static void waitClickEnter(){
        System.out.print("Нажмите Enter, чтобы продолжить...");
        sc.nextLine();
        sc.nextLine();
    }
}
