package com.eremin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Day3PartOneTest {
    static String fileName = "input.txt";
    static Day3PartOne day3 = new Day3PartOne();

    @Test
    void multiplySomeNumbers() {
        int expected = 161;
        int actual = day3.multiplySomeNumbers(fileName);
        assertEquals(expected, actual);
    }
}
