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


public class Day4PartOne {

    public int countWord(String fileName) {
        int times = 0;
        char[][] charArrayFromFile = getCharArrayFromFile(getURIOfResource(fileName));
        for (char[] chars : charArrayFromFile) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        // TODO
        return times;
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

    private char[][] getCharArrayFromFile(Path path) {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allLines.stream().map(String::toCharArray).toArray(char[][]::new);
    }
}
