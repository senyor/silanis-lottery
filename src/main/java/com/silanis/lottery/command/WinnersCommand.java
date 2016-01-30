package com.silanis.lottery.command;

import com.silanis.lottery.Lottery;

import java.util.Scanner;

import static com.silanis.lottery.OutputHandler.addMessage;

/**
 * Implementation of winners command
 *
 * @author Andrei Kuziakov
 */
public class WinnersCommand implements Command {

    private Lottery lottery;

    public WinnersCommand(Lottery lottery) {
        this.lottery = lottery;
    }

    @Override
    public void execute() {
        if (lottery.isDrawPerformed()) {
            addMessage(lottery.winners() + "\n");
        } else {
            addMessage("No draws performed yet!\n");
        }
    }
}
