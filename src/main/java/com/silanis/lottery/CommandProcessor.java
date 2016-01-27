package com.silanis.lottery;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Interact with user and perform actions
 *
 * @author Andrei Kuziakov
 */
public class CommandProcessor {

    public static final String COMMAND_PURCHASE = "purchase";
    public static final String COMMAND_DRAW = "draw";
    public static final String COMMAND_WINNERS = "winners";
    public static final String COMMAND_QUIT = "quit";

    private Lottery lottery;

    public CommandProcessor(Lottery lottery) {
        this.lottery = lottery;
    }

    public void process() {

        while (true) {
            Scanner userInput = new Scanner(System.in);

            System.out.printf("Please enter command (%s, %s, %s, %s):\n> ",
                    COMMAND_PURCHASE, COMMAND_DRAW, COMMAND_WINNERS, COMMAND_QUIT);

            String command = "";

            try {
                command = userInput.next();
            } catch (NoSuchElementException ignored) {

            }

            System.out.printf("Entered command \"%s\"\n", command);

            switch (command) {
                case COMMAND_PURCHASE:
                    if (lottery.isSaleComplete()) {
                        System.out.println("Sorry, but no more tickets left for purchase!");
                    } else {
                        System.out.print("Enter your name: ");
                        String name = userInput.next();
                        int ticketNumber = lottery.purchase(name);
                        System.out.printf("Ticket %d purchased by %s\n", ticketNumber, name);
                    }
                    break;
                case COMMAND_DRAW:
                    if (lottery.isSaleStart()) {
                        lottery.draw();
                        System.out.printf("Pot amount left after draw is %d$\n", lottery.getPotAmount());
                    } else {
                        System.out.println("No tickets purchased, please buy at least one before draw!");
                    }
                    break;
                case COMMAND_WINNERS:
                    if (lottery.isDrawPerformed()) {
                        System.out.println(lottery.winners());
                    } else {
                        System.out.println("No draws performed yet!");
                    }
                    break;
                case COMMAND_QUIT:
                    System.out.println("\nGood to see you! Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command, please try again!");
            }
        }
    }
}