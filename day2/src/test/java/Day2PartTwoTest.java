import com.eremin.Day2PartOne;
import com.eremin.Day2PartTwo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class Day2PartTwoTest {
    static List<String> allLinesFromFile = null;
    static Path path = null;
    static Map<Integer, List<Integer>> allLevelsPerLine = new HashMap<>();
    static Day2PartTwo day2 = new Day2PartTwo();

    @BeforeAll
    static void setUpBeforeClass() {
        try {
            path = Paths.get(Objects.requireNonNull(Day2PartTwoTest.class.getClassLoader().getResource("input.txt")).toURI());
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

    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    void shouldReturnTrueOnMethodIsAllIncrease(int param) {
        assertTrue(day2.isAllIncrease(allLevelsPerLine.get(param)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 3, 4})
    void shouldReturnFalseOnMethodIsAllIncrease(int param) {
        assertFalse(day2.isAllIncrease(allLevelsPerLine.get(param)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    void shouldReturnTrueOnMethodIsAllDecrease(int param) {
        assertTrue(day2.isAllDecrease(allLevelsPerLine.get(param)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4, 5})
    void shouldReturnFalseOnMethodIsAllDecrease(int param) {
        assertFalse(day2.isAllDecrease(allLevelsPerLine.get(param)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 4, 5})
    void shouldReturnTrueOnMethodIsDifferLessThanFour(int param) {
        assertTrue(day2.isDifferLessThanFour(allLevelsPerLine.get(param)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void shouldReturnFalseOnMethodIsDifferLessThanFour(int param) {
        assertFalse(day2.isDifferLessThanFour(allLevelsPerLine.get(param)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 5})
    void shouldReturnTrueOnMethodIsDifferMoreThanZero(int param) {
        assertTrue(day2.isDifferMoreThanZero(allLevelsPerLine.get(param)));
    }

    @Test
    void shouldReturnFalseOnMethodIsDifferMoreThanZero() {
        assertFalse(day2.isDifferMoreThanZero(allLevelsPerLine.get(4)));
    }

    @Test
    void shouldReturnCorrectValueOfSafeLevels() {
        int expected = 4;
        int actual = day2.getActuallySafeLevels("input.txt");
        assertEquals(expected, actual);
    }
}
