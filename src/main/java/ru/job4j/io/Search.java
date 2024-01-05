package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private static boolean isArgumentsValid(String[] args) {
        return args.length >= 2 && Files.exists(Path.of(args[0]));
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        if (!isArgumentsValid(args)) {
            throw new IllegalArgumentException("A valid path and file suffix required");
        }
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}