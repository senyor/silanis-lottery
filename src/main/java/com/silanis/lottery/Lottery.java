package com.silanis.lottery;

/**
 * Lottery interface
 *
 * @author Andrei Kuziakov
 */
public interface Lottery {

    /**
     * Purchase a ticket
     *
     * @param name buyer's name
     * @return ticket number
     */
    int purchase(String name);

    /**
     * Draw a lottery
     */
    void draw();

    /**
     * Prepare winners summary
     * @return String containing winners summary information
     */
    String winners();

    /**
     * Check whether at least one ticket is sold or not
     * @return true if at least one ticket is sold
     */
    boolean isSaleStart();

    /**
     * Check whether the sale is complete or not
     * @return true if all tickets are sold out
     */
    boolean isSaleComplete();

    /**
     * Check if at least one draw is performed
     * @return true if draw is performed
     */
    boolean isDrawPerformed();

    /**
     * Find out amount of funds in the pot
     * @return current amount of funds in the pot
     */
    int getPotAmount();

    /**
     * Find out amount of funds won in last draw
     * @return amount of won funds in last draw
     */
    int getWinAmount();

    /**
     * Reinitialize lottery
     */
    void restart();
}
