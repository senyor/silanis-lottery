package com.silanis.lottery;

import java.util.*;

import static com.silanis.lottery.Helper.initListWithRange;
import static com.silanis.lottery.Helper.round;

/**
 * Performs the game of Silanis Lottery
 *
 * @author Andrei Kuziakov
 */
public class SilanisLottery implements Lottery {

    public static final int INITIAL_POT = 200;
    public static final int TICKET_COST = 10;

    public static final int BALLS_TO_DRAW = 3;
    public static final float[] WIN_RATIO = {0.75f, 0.15f, 0.10f};

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 50;

    private int potAmount = INITIAL_POT;
    private int potWinAmount = 0;

    private List<Integer> availableTickets = new ArrayList<>();
    private Map<Integer, String> purchasedTickets = new HashMap<>();

    private List<Integer> balls = new ArrayList<>();
    private List<String> winners = new ArrayList<>();

    private static SilanisLottery lottery = null;

    /**
     * Single instance of {@link SilanisLottery} class to be returned by this method
     *
     * @return an only instance of a SilanisLottery
     */
    public static SilanisLottery getInstance() {
        if (lottery == null) {
            lottery = new SilanisLottery();
        }
        return lottery;
    }

    private SilanisLottery() {
        initSequence();
    }

    @Override
    public int purchase(String name) {
        Integer selectedNumber = availableTickets.remove(0);
        purchasedTickets.put(selectedNumber, name);
        potAmount += TICKET_COST;
        return selectedNumber;
    }

    @Override
    public void draw() {
        winners.clear();
        Collections.shuffle(balls, new Random());

        potWinAmount = round(potAmount * 0.5);

        double targetPotAmount = (float) (potAmount) * 0.5;
        double finalWinAmount = 0;

        for (int i = 0; i < BALLS_TO_DRAW; i++) {
            double amount = getWinAmount() * WIN_RATIO[i];
            String winner = purchasedTickets.get(balls.get(i));

            // get funds from the pot for each winner
            if (winner != null) {
                targetPotAmount -= amount;
                finalWinAmount += amount;
                winners.add(String.format("%s: %d$", winner, round(amount)));
            } else {
                winners.add("-");
            }
        }

        // recalculated win amount due to each winner
        potAmount = round(targetPotAmount);
        potWinAmount = round(finalWinAmount);

        initSequence();
    }

    @Override
    public String winners() {
        // init max columnWidth
        int columnWidth = 8;

        // find max column width of all winners
        for (String winner : winners) {
            columnWidth = Math.max(columnWidth, winner.length());
        }

        StringBuilder winnersSummary = new StringBuilder();

        String col = "%-" + columnWidth + "s   ";
        winnersSummary.append(String.format(col + col + col + "\n", "1st ball", "2nd ball", "3rd ball"));

        for (String winner : winners) {
            winnersSummary.append(String.format(col, winner));
        }
        winnersSummary.append("\n");
        return winnersSummary.toString();
    }

    @Override
    public boolean isSaleStart() {
        return !purchasedTickets.isEmpty();
    }

    @Override
    public boolean isSaleComplete() {
        return availableTickets.isEmpty();
    }

    @Override
    public boolean isDrawPerformed() {
        return !winners.isEmpty();
    }

    @Override
    public int getPotAmount() {
        return potAmount;
    }

    @Override
    public int getWinAmount() {
        return potWinAmount;
    }

    @Override
    public void restart() {
        potAmount = INITIAL_POT;
        potWinAmount = 0;
        winners.clear();
        initSequence();
    }

    private void initSequence() {
        availableTickets.clear();
        initListWithRange(availableTickets, MIN_NUMBER, MAX_NUMBER);

        balls.clear();
        initListWithRange(balls, MIN_NUMBER, MAX_NUMBER);

        purchasedTickets.clear();
    }
}
