package com.silanis.lottery;

import com.silanis.lottery.command.Command;

import static com.silanis.lottery.InputHandler.getInputString;
import static com.silanis.lottery.OutputHandler.*;

/**
 * Interact with user and perform actions
 *
 * @author Andrei Kuziakov
 */
public class CommandProcessor {

    private Lottery lottery;

    public CommandProcessor(Lottery lottery) {
        this.lottery = lottery;
    }

    public void process() {
        CommandFactory commandFactory = new CommandFactory(lottery);

        while (true) {
            addMessage(String.format("Please enter command %s:\n> ", commandFactory.getCommands()));

            if (!InputHandler.getUserInput().hasNext()) {
                InputHandler.closeInput();
                addErrorMessage("Abnormal input. Exiting.");
                System.exit(1);
            }
            String userCommand = getInputString().trim();
            addMessage(String.format("Entered command \"%s\"\n", userCommand));

            Command command = commandFactory.getCommand(userCommand);
            command.execute();
        }
    }
}