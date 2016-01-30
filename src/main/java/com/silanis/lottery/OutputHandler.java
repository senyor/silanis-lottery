package com.silanis.lottery;

/**
 * @author Andrei Kuziakov
 */
public class OutputHandler {

    public static void addMessage(String message) {
        System.out.print(message);
    }

    public static void addErrorMessage(String message) {
        System.err.print(message);
    }
}
