package com.eremin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day3PartOne {

    public int multiplySomeNumbers(String fileName) {

        String stringFromFile = getOneStringFromFile(getURIOfResource(fileName));
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        List<String> listOfMatches = getListOfMatches(stringFromFile, pattern);

        // тут или парсим два числа, перемножаем, складываем и по кругу
        // или сначала все парсим в два листа интеджеров, а потом их перемножаем

        return 0;
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
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }
}
