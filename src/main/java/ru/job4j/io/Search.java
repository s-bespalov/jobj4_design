package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private static void checkArguments(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        var path = Path.of(args[0]);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("The directory '%s' is not exist", args[0]));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("The path '%s' is not directory", args[0]));
        }
        if (args[1].length() < 2 || args[1].charAt(0) != '.') {
            throw new IllegalArgumentException(String.format("'%s' is not correct file extension", args[1]));
        }
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        checkArguments(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}