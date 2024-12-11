package com.eremin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4PartOneTest {
    static String fileName = "input.txt";
    static Day4PartOne day4 = new Day4PartOne();

    @Test
    void multiplySomeNumbers() {
        int expected = 18;
        int actual = day4.countWord(fileName);
        assertEquals(expected, actual);
    }
}
