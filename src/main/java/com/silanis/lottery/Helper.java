package com.silanis.lottery;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Helper class to perform various operations
 */
public class Helper {

    /**
     * Round double number to the nearest integer by adding 0.5 and casting to int
     *
     * @param num number to be round
     * @return value round to the nearest integer
     */
    public static int round(double num) {
        return (int) (num + 0.5);
    }

    /**
     * Initialize given list with integers in inclusive range of minNumber to maxNumber
     *
     * @param list a list to initialize
     * @param minNumber inclusive range minimum
     * @param maxNumber inclusive range maximum
     */
    public static void initListWithRange(List<Integer> list, int minNumber, int maxNumber) {
        IntStream.range(minNumber, maxNumber + 1).forEachOrdered(list::add);
    }
}