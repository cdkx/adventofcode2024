package com.eremin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day3PartTwoTest {
    static String fileName = "input2.txt";
    static Day3PartTwo day3 = new Day3PartTwo();

    @Test
    void multiplySomeNumbers() {
        int expected = 48;
        int actual = day3.multiplySomeNumbers(fileName);
        assertEquals(expected, actual);
    }
}
