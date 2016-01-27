package com.silanis.lottery;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link Helper} class
 *
 * @author Andrei Kuziakov
 */
public class HelperTest {

    @Test
    public void roundTest() {
        // test with doubles
        assertEquals(1, Helper.round(1d));
        assertEquals(1, Helper.round(1.000001d));
        assertEquals(1, Helper.round(1.1d));
        assertEquals(2, Helper.round(1.5d));
        assertEquals(2, Helper.round(1.9d));
        assertEquals(2, Helper.round(1.999999d));

        // test with floats
        assertEquals(1, Helper.round(1f));
        assertEquals(1, Helper.round(1.000001f));
        assertEquals(1, Helper.round(1.1f));
        assertEquals(2, Helper.round(1.5f));
        assertEquals(2, Helper.round(1.9f));
        assertEquals(2, Helper.round(1.999999f));
    }

    @Test
    public void initListWithRangeTest() {
        ArrayList<Integer> list = new ArrayList<>();

        Helper.initListWithRange(list, 1, 5);

        assertEquals(5, list.size());
        assertArrayEquals(new Integer[] {1, 2, 3, 4, 5}, list.toArray(new Integer[list.size()]));
    }
}
