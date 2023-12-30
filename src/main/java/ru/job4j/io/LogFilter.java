package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        var result = new LinkedList<String>();
        try (var input = new BufferedReader(new FileReader(file))) {
            input.lines().forEach(el -> {
                var split = el.split(" ");
                if (Integer.parseInt(split[split.length - 2]) == 404) {
                    result.add(el);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}