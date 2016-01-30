package com.silanis.lottery.command;

import com.silanis.lottery.Lottery;

import static com.silanis.lottery.InputHandler.getInputString;
import static com.silanis.lottery.OutputHandler.addMessage;

/**
 * Implementation of purchase command
 *
 * @author Andrei Kuziakov
 */
public class PurchaseCommand implements Command {

    private Lottery lottery;

    public PurchaseCommand(Lottery lottery) {
        this.lottery = lottery;
    }

    @Override
    public void execute() {
        if (lottery.isSaleComplete()) {
            addMessage("Sorry, but no more tickets left for purchase!\n");
        } else {
            addMessage("Enter your name: ");
            String name = getInputString();
            int ticketNumber = lottery.purchase(name);
            addMessage(String.format("Ticket %d purchased by %s\n", ticketNumber, name));
        }
    }
}
