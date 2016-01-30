package com.silanis.lottery.command;

import static com.silanis.lottery.OutputHandler.addMessage;

/**
 * Implementation of unknown command
 *
 * @author Andrei Kuziakov
 */
public class UnknownCommand implements Command {

    @Override
    public void execute() {
        addMessage("Unknown command, please try again!\n");
    }
}
