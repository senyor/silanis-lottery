package com.silanis.lottery;

import com.silanis.lottery.command.*;

import java.util.Arrays;
import java.util.List;

/**
 * A factory to create command instances
 *
 * @author Andrei Kuziakov
 */
public class CommandFactory {

    public static final String COMMAND_PURCHASE = "purchase";
    public static final String COMMAND_DRAW = "draw";
    public static final String COMMAND_WINNERS = "winners";
    public static final String COMMAND_QUIT = "quit";

    private Command purchase;
    private Command draw;
    private Command winners;
    private Command quit;
    private Command unknown;

    public CommandFactory(Lottery lottery) {
        this.purchase = new PurchaseCommand(lottery);
        this.draw = new DrawCommand(lottery);
        this.winners = new WinnersCommand(lottery);
        this.quit = new QuitCommand();
        this.unknown = new UnknownCommand();
    }

    public List<String> getCommands() {
        return Arrays.asList(COMMAND_PURCHASE, COMMAND_DRAW, COMMAND_WINNERS, COMMAND_QUIT);
    }

    public Command getCommand(String name) {
        switch (name) {
            case COMMAND_PURCHASE:
                return purchase;
            case COMMAND_DRAW:
                return draw;
            case COMMAND_WINNERS:
                return winners;
            case COMMAND_QUIT:
                return quit;
            default:
                return unknown;
        }
    }
}
