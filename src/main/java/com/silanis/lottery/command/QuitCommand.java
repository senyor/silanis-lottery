package com.silanis.lottery.command;

import com.silanis.lottery.OutputHandler;

import static com.silanis.lottery.InputHandler.closeInput;

/**
 * Implementation of quit command
 *
 * @author Andrei Kuziakov
 */
public class QuitCommand implements Command {

    @Override
    public void execute() {
        OutputHandler.addMessage("\nGood to see you! Bye!\n");
        closeInput();
        System.exit(0);
    }
}
