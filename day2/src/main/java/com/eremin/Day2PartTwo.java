package com.eremin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Day2PartTwo {

    public int result() {
        Map<Integer, List<Integer>> allLevelsPerLine = getUnusualDataFromFile(getPath());

        int result = 0;
        for (int i = 0; i < allLevelsPerLine.size(); i++) {
            var levels = allLevelsPerLine.get(i);
            if ((isAllIncrease(levels) || isAllDecrease(levels))
                    && (isDifferLessThanFour(levels) && isDifferMoreThanZero(levels))) {
                result++;
            }
        }
        return result;
    }

    public boolean isAllIncrease(List<Integer> levels) {
        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i) >= levels.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllDecrease(List<Integer> levels) {
        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i) <= levels.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDifferLessThanFour(List<Integer> levels) {
        for (int i = 0; i < levels.size() - 1; i++) {
            var j = levels.get(i) - levels.get(i + 1);
            var levelDiff = j > 0 ? j : -j;

            if (levelDiff >= 4) {
                return false;
            }
        }
        return true;
    }

    public boolean isDifferMoreThanZero(List<Integer> levels) {
        for (int i = 0; i < levels.size() - 1; i++) {
            var j = levels.get(i) - levels.get(i + 1);
            var levelDiff = j > 0 ? j : -j;

            if (levelDiff <= 0) {
                return false;
            }
        }
        return true;
    }

    private Path getPath() {
        Path path;
        try {
            path = Paths.get(Objects.requireNonNull(getClass()
                            .getClassLoader()
                            .getResource("input.txt"))
                    .toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    private Map<Integer, List<Integer>> getUnusualDataFromFile(Path path) {
        return getIntegerListMap(getStringsFromFile(path));
    }

    private List<String> getStringsFromFile(Path path) {
        List<String> allLinesFromFile;
        try {
            allLinesFromFile = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allLinesFromFile;
    }

    private Map<Integer, List<Integer>> getIntegerListMap(List<String> allLinesFromFile) {
        Map<Integer, List<Integer>> allLevelsPerLine = new HashMap<>();
        for (int i = 0; i < allLinesFromFile.size(); i++) {
            String line = allLinesFromFile.get(i);
            String[] stringLevels = line.split("\\s+");
            List<Integer> levels = new ArrayList<>();

            for (String stringLevel : stringLevels) {
                var level = Integer.parseInt(stringLevel);
                levels.add(level);
            }

            allLevelsPerLine.put(i, levels);
        }
        return allLevelsPerLine;
    }
}