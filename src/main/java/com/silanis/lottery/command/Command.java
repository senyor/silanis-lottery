package com.silanis.lottery.command;

/**
 *
 * @author Andrei Kuziakov
 */
public interface Command {

    /**
     * Behavior of implementor could be changed by implementing this method
     */
    void execute();
}
