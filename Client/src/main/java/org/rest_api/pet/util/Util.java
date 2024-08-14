package org.rest_api.pet.util;


import java.util.Locale;
import java.util.Scanner;

public class Util {

    public  static Scanner sc = new Scanner(System.in);
    static {
        sc.useLocale(Locale.UK);
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
