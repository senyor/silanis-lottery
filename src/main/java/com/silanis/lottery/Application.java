package com.silanis.lottery;

/**
 *
 * Silanis Lottery Application<br/>
 *
 * please see <b>readme.md</b> for description
 *
 * @author Andrei Kuziakov
 */
public class Application {

    public static void main(String[] args) {
        Lottery lottery = SilanisLottery.getInstance();

        CommandProcessor commandProcessor = new CommandProcessor(lottery);
        commandProcessor.process();
    }
}