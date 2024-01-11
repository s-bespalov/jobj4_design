package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        var file = Path.of(argsName.get("path"));
        var delimiter = argsName.get("delimiter");
        var filter = new Scanner(argsName.get("filter")).useDelimiter(",").tokens().toList();
        var out = argsName.get("out");
        List<List<String>> records;
        try (var lines = new Scanner(file).useDelimiter(System.lineSeparator()).tokens()) {
            records = lines
                    .map(line -> new Scanner(line).useDelimiter(delimiter).tokens().toList())
                    .toList();
        }
        var filterIndexes = filter
                .stream()
                .map(records.get(0)::indexOf)
                .filter(i -> i != -1)
                .toList();
        var filteredLines = records
                .stream()
                .map(record -> filterIndexes
                        .stream()
                        .map(record::get)
                        .toList())
                .map(record -> String.join(delimiter, record))
                .toList();
        if (Objects.equals(out, "stdout")) {
            filteredLines.forEach(System.out::println);
        } else {
            try (var output = new PrintStream(new BufferedOutputStream(new FileOutputStream(out)))) {
                filteredLines.forEach(output::println);
            }
        }
    }

    public static void checkArgument(ArgsName args) {
        var path = Path.of(args.get("path"));
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IllegalArgumentException(String.format("'%s' is not a file", path));
        }
        var delimiter = args.get("delimiter");
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("Delimiter is empty");
        }
        var filter = args.get("filter");
        if (filter.isEmpty()) {
            throw new IllegalArgumentException("Filter is empty");
        }
        var out = Path.of(args.get("out"));
        if (!Objects.equals(out.toString(), "stdout") && Files.exists(out) && !Files.isRegularFile(out)) {
            throw new IllegalArgumentException(String.format("'%s' is not a regular file", out));
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        checkArgument(argsName);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}