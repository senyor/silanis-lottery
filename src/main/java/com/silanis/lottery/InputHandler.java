package com.silanis.lottery;

import java.util.Scanner;

/**
 * @author Andrei Kuziakov
 */
public class InputHandler {

    private static Scanner userInput;

    public static Scanner getUserInput() {
        if (userInput == null) {
            userInput = new Scanner(System.in);
        }
        return userInput;
    }

    public static String getInputString() {
        return getUserInput().nextLine();
    }

    public static void closeInput() {
        getUserInput().close();
    }
}
