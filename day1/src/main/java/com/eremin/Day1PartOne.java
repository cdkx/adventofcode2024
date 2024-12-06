package com.eremin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Day1PartOne {
    public int result() {
        
        // считать все строки из файла
        Path path;
        try {
            path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("input.txt")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<String> allLinesFromFile;
        try {
            allLinesFromFile = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // разделить в два листа
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (String s : allLinesFromFile) {
            String[] split = s.split("\\s+");
            list1.add(Integer.parseInt(split[0]));
            list2.add(Integer.parseInt(split[1]));
        }

        // отсортировать
        Collections.sort(list1);
        Collections.sort(list2);

        // вычислить разницу -> в новый лист
        List<Integer> listOfResult = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            int j = (list1.get(i) - list2.get(i));
            int distance = j > 0 ? j : -j;
            listOfResult.add(distance);
        }

        // сумма всех элементов
        return listOfResult
                .stream()
                .mapToInt(integer -> integer)
                .sum();
    }
}
