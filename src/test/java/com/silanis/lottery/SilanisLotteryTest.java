package com.silanis.lottery;

import org.junit.Before;
import org.junit.Test;

import static com.silanis.lottery.SilanisLottery.*;
import static org.junit.Assert.*;

/**
 *
 * Tests for {@link SilanisLottery} class
 *
 * @author Andrei Kuziakov
 */
public class SilanisLotteryTest {

    Lottery lottery = null;

    @Before
    public void setUp() {
        lottery = SilanisLottery.getInstance();
    }

    @Test
    public void testPurchaseSequence() {

        lottery.restart();

        assertEquals(INITIAL_POT, lottery.getPotAmount());
        assertEquals(0, lottery.getWinAmount());

        assertFalse(lottery.isDrawPerformed());
        assertFalse(lottery.isSaleStart());
        assertFalse(lottery.isSaleComplete());

        int ticketNo = lottery.purchase("Andrew");
        assertEquals(1, ticketNo);
        assertEquals(INITIAL_POT + TICKET_COST, lottery.getPotAmount());

        assertTrue(lottery.isSaleStart());

        ticketNo = lottery.purchase("John");
        assertEquals(2, ticketNo);
        assertEquals(INITIAL_POT + TICKET_COST * 2, lottery.getPotAmount());

        // allow the same person to buy multiple tickets
        ticketNo = lottery.purchase("John");
        assertEquals(3, ticketNo);
        assertEquals(INITIAL_POT + TICKET_COST * 3, lottery.getPotAmount());

        // ensure the win amount is not changed
        assertEquals(0, lottery.getWinAmount());

        // sale should not be completed yet
        assertFalse(lottery.isSaleComplete());
    }

    @Test
    public void testSellOut() {

        // check if we can buy any tickets at all
        if (lottery.isSaleStart() && lottery.isSaleComplete()) {
            return;
        }

        int potAmount =  lottery.getPotAmount();

        // try to buy one ticket
        int ticketNo = lottery.purchase("andrew");

        assertTrue(lottery.isSaleStart());

        assertEquals(potAmount + TICKET_COST, lottery.getPotAmount());

        if (ticketNo < MAX_NUMBER) {
            assertFalse(lottery.isSaleComplete());
        } else {
            return;
        }

        int lastTicketNo = buyAllTickets(ticketNo + 1, MAX_NUMBER);

        assertEquals(potAmount + TICKET_COST * (lastTicketNo - ticketNo + 1), lottery.getPotAmount());

        assertTrue(lottery.isSaleStart());
        assertTrue(lottery.isSaleComplete());
    }

    @Test
    public void testDraw() {

        lottery.restart();

        // buy all the tickets
        int potAmount = lottery.getPotAmount();

        // try to buy out all the tickets
        buyAllTickets(MIN_NUMBER, MAX_NUMBER);

        assertEquals(potAmount + TICKET_COST * (MAX_NUMBER - MIN_NUMBER + 1), lottery.getPotAmount());

        assertTrue(lottery.isSaleStart());
        assertTrue(lottery.isSaleComplete());

        lottery.draw();

        assertTrue(lottery.isDrawPerformed());

        assertEquals(Helper.round((potAmount + TICKET_COST * (MAX_NUMBER - MIN_NUMBER + 1)) * 0.5),
                lottery.getWinAmount());
    }

    @Test
    public void testWinners() {
        lottery.restart();

        buyAllTickets(MIN_NUMBER, MAX_NUMBER);
        lottery.draw();

        String[] winners = lottery.winners().split("\n");

        assertEquals(2, winners.length);
        assertTrue(winners[0].matches("((1st|2nd|3rd)\\sball\\s+){3}"));
        assertTrue(winners[1].matches("((andrew\\d+:\\s\\d+\\$|-)\\s+){3}"));
    }

    private int buyAllTickets(int minNumber, int maxNumber) {
        int lastNo = 0;
        for (int i = minNumber; i <= maxNumber; i++) {
            lastNo = lottery.purchase(String.format("andrew%d", i));
        }
        return lastNo;
    }
}
