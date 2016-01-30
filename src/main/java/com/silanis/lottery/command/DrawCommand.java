package com.silanis.lottery.command;

import com.silanis.lottery.Lottery;

import static com.silanis.lottery.OutputHandler.addMessage;

/**
 * Implementation of draw command
 *
 * @author Andrei Kuziakov
 */
public class DrawCommand implements Command {

    private Lottery lottery;

    public DrawCommand(Lottery lottery) {
        this.lottery = lottery;
    }

    @Override
    public void execute() {
        if (lottery.isSaleStart()) {
            lottery.draw();
            addMessage(String.format("Pot amount left after draw is %d$\n", lottery.getPotAmount()));
        } else {
            addMessage("No tickets purchased, please buy at least one before draw!\n");
        }
    }
}
