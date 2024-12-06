import com.eremin.Day2PartOne;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class Day2PartOneTest {
    static List<String> allLinesFromFile = null;
    static Path path = null;
    static Map<Integer, List<Integer>> allLevelsPerLine = new HashMap<>();
    static Day2PartOne day2 = new Day2PartOne();

    @BeforeAll
    static void setUpBeforeClass() {
        try {
            path = Paths.get(Objects.requireNonNull(Day2PartOneTest.class.getClassLoader().getResource("input.txt")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            allLinesFromFile = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < allLinesFromFile.size(); i++) {
            String line = allLinesFromFile.get(i);
            String[] stringLevels = line.split("\\s+");
            List<Integer> levels = new ArrayList<>();

            for (String stringLevel : stringLevels) {
                int level = Integer.parseInt(stringLevel);
                levels.add(level);
            }

            allLevelsPerLine.put(i, levels);
        }
    }

    @Test
    void shouldReturnTrueOnMethodIsAllIncrease() {
        assertTrue(day2.isAllIncrease(allLevelsPerLine.get(1)));
        assertTrue(day2.isAllIncrease(allLevelsPerLine.get(5)));
    }

    @Test
    void shouldReturnFalseOnMethodIsAllIncrease() {
        assertFalse(day2.isAllIncrease(allLevelsPerLine.get(0)));
        assertFalse(day2.isAllIncrease(allLevelsPerLine.get(2)));
        assertFalse(day2.isAllIncrease(allLevelsPerLine.get(3)));
        assertFalse(day2.isAllIncrease(allLevelsPerLine.get(4)));
    }

    @Test
    void shouldReturnTrueOnMethodIsAllDecrease() {
        assertTrue(day2.isAllDecrease(allLevelsPerLine.get(0)));
        assertTrue(day2.isAllDecrease(allLevelsPerLine.get(2)));
    }

    @Test
    void shouldReturnFalseOnMethodIsAllDecrease() {
        assertFalse(day2.isAllDecrease(allLevelsPerLine.get(1)));
        assertFalse(day2.isAllDecrease(allLevelsPerLine.get(3)));
        assertFalse(day2.isAllDecrease(allLevelsPerLine.get(4)));
        assertFalse(day2.isAllDecrease(allLevelsPerLine.get(5)));
    }

    @Test
    void shouldReturnTrueOnMethodIsDifferLessThanFour() {
        assertTrue(day2.isDifferLessThanFour(allLevelsPerLine.get(0)));
        assertTrue(day2.isDifferLessThanFour(allLevelsPerLine.get(3)));
        assertTrue(day2.isDifferLessThanFour(allLevelsPerLine.get(4)));
        assertTrue(day2.isDifferLessThanFour(allLevelsPerLine.get(5)));
    }

    @Test
    void shouldReturnFalseOnMethodIsDifferLessThanFour() {
        assertFalse(day2.isDifferLessThanFour(allLevelsPerLine.get(1)));
        assertFalse(day2.isDifferLessThanFour(allLevelsPerLine.get(2)));
    }

    @Test
    void shouldReturnTrueOnMethodIsDifferMoreThanZero() {
        assertTrue(day2.isDifferMoreThanZero(allLevelsPerLine.get(0)));
        assertTrue(day2.isDifferMoreThanZero(allLevelsPerLine.get(1)));
        assertTrue(day2.isDifferMoreThanZero(allLevelsPerLine.get(2)));
        assertTrue(day2.isDifferMoreThanZero(allLevelsPerLine.get(3)));
        assertTrue(day2.isDifferMoreThanZero(allLevelsPerLine.get(5)));
    }

    @Test
    void shouldReturnFalseOnMethodIsDifferMoreThanZero() {
        assertFalse(day2.isDifferMoreThanZero(allLevelsPerLine.get(4)));
    }

    @Test
    void shouldReturnCorrectValueOfSafeLevels() {
        int expected = 2;
        int actual = day2.result();
        assertEquals(expected, actual);
    }
}
