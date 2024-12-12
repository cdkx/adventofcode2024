package com.eremin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day3PartTwo {

    public int multiplySomeNumbers(String fileName) {
        String stringFromFile = getOneStringFromFile(getURIOfResource(fileName));

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|don't\\(\\)|do\\(\\)");
        List<String> listOfMatches = getListOfMatches(stringFromFile, pattern);

        Pattern pattern2 = Pattern.compile("\\d+");
        List<Integer> listOfIntegers = getIntegers(listOfMatches, pattern2);

        return getMultiplications(listOfIntegers);
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

    private String getOneStringFromFile(Path path) {
        List<String> allLinesFromFile;
        try {
            allLinesFromFile = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.join("", allLinesFromFile);
    }

    private List<String> getListOfMatches(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        List<String> matches = new ArrayList<>();
        boolean isIgnore = false;
        while (matcher.find()) {
            if (matcher.group().equals("do()")) {
                isIgnore = false;
            } else if (matcher.group().equals("don't()")) {
                isIgnore = true;
            }
            if (!isIgnore) {
                matches.add(matcher.group());
            }
        }
        return matches;
    }

    private List<Integer> getIntegers(List<String> listOfMatches, Pattern pattern) {
        Matcher matcher = pattern.matcher(listOfMatches.toString());
        List<Integer> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(Integer.valueOf(matcher.group()));
        }
        return matches;
    }

    private static int getMultiplications(List<Integer> listOfIntegers) {
        int result = 0;
        for (int i = 0; i < listOfIntegers.size() / 2; i++) {
            int first = listOfIntegers.get(i * 2);
            int second = listOfIntegers.get(i * 2 + 1);
            result += first * second;
        }
        return result;
    }
}
