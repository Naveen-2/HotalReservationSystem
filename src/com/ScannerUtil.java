package com;

import java.util.Scanner;

/**
 * Scanner utility class used to get user input after printing a message
 */
public class ScannerUtil {

    private final static Scanner input = new Scanner(System.in);

    /*
    getString method to get String input after printing a message
     */
    public static String getString(String text) {
        System.out.print(text);
        return input.next();
    }

    /*
    getInt method to get Integer input after printing the given message
     */
    public static int getInt(String text) {
        System.out.print(text);
        return input.nextInt();
    }

    /*
    getDouble method to get Integer input after printing the given message
     */
    public static Double getDouble(String text) {
        System.out.print(text);
        return input.nextDouble();
    }

}