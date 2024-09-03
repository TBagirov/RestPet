package org.rest_api.pet;

import org.rest_api.pet.controllers.ClientController;

import static org.rest_api.pet.util.Util.*;

/**
 * Hello world!
 *
 */
public class Client {

    public static void main( String[] args ) {

        ClientController controller = new ClientController();

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
                    controller.addSensor();

                    break;
                }

                case 2: {
                    controller.addMeasurement();

                    break;
                }

                case 3: {
                    controller.getMeasurements();

                    break;
                }

                case 4:{
                    controller.getRainyDaysCount();

                    break;
                }
            } // switch

            clearConsole();
        }// while

    }




}
