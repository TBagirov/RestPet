package org.rest_api.pet.controllers;

import org.rest_api.pet.app.App;
import org.rest_api.pet.models.Measurement;
import org.rest_api.pet.models.Sensor;

import static org.rest_api.pet.util.Util.*;

public class ClientController {

    private App app = new App();

    public ClientController(){}
    public ClientController(App app){
        this.app = app;
    }

    public void addSensor() {
        System.out.print("Введите название добавляемого сенсора: ");
        String name = sc.next();
        System.out.println("\nРезультат выполнения запроса: " +  app.addSensor(name));

        waitClickEnter();
    }

    public void addMeasurement(){
        Measurement measurement = new Measurement();

        System.out.print("\nВведите температуру воздуха: ");
        measurement.setValue(sc.nextFloat());

        System.out.print("Введите зарегестрировал ли сенсор дождь: ");
        measurement.setRaining(sc.nextBoolean());

        System.out.print("Введите название сенсора, который провел измерения: ");
        measurement.setSensor(new Sensor(sc.next()));

        System.out.println("\nРезультат выполнения запроса: " +  app.addMeasurement(measurement));

        waitClickEnter();
    }


    public void getMeasurements(){
        clearConsole();
        System.out.println();
        app.getMeasurements().getMeasurementList().forEach(System.out::println);

        System.out.println();
        waitClickEnter();}


    public void getRainyDaysCount(){
        System.out.println("\nКоличество дождливый дней = " + app.getRainyDaysCount() + "\n");

        waitClickEnter();
    }

}
