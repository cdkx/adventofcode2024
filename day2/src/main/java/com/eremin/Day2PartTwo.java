package com.eremin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Day2PartTwo {

    public int getActuallySafeLevels(String fileName) {
        Map<Integer, List<Integer>> allLevelsPerLine = getUnusualDataFromFile(getURIOfResource(fileName));
        int countOfSafeLevels = 0;

        for (int i = 0; i < allLevelsPerLine.size(); i++) {
            List<Integer> levels = allLevelsPerLine.get(i);
            if (isSafe(levels)) {
                countOfSafeLevels++;
            } else {
                for (int j = 0; j < levels.size(); j++) {
                    List<Integer> copyOfLevels = new ArrayList<>(levels);
                    copyOfLevels.remove(j);

                    if (isSafe(copyOfLevels)) {
                        countOfSafeLevels++;
                        break;
                    }
                }
            }
        }
        return countOfSafeLevels;
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

    private Path getURIOfResource(String fileName) {
        Path path;
        try {
            path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
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
            List<Integer> levels = Arrays.stream(stringLevels).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            allLevelsPerLine.put(i, levels);
        }
        return allLevelsPerLine;
    }

    private boolean isSafe(List<Integer> levels) {
        return (isAllIncrease(levels) || isAllDecrease(levels)) && (isDifferLessThanFour(levels) && isDifferMoreThanZero(levels));
    }
}
