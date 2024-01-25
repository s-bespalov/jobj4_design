package ru.job4j.io.find;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class FileFinder {
    private Path dir;
    private String name;
    private String type;

    FileFinder(Path dir, String name, String type) {
        this.dir = dir;
        this.name = name;
        this.type = type;
        if (Objects.equals(this.type, "mask")) {
            this.name = this.name.replaceAll("\\.", "\\.")
                    .replaceAll("\\?", ".")
                    .replaceAll("\\*", ".*");
        }
    }

    private boolean checkFileName(Path filePath) {
        var fileName = filePath.getFileName().toString();
        var rsl = false;
        rsl = switch (type) {
            case "name" -> Objects.equals(fileName, name);
            case "regex", "mask" -> Pattern.compile(name).matcher(fileName).matches();
            default -> false;
        };
        return rsl;
    }

    public List<Path> find() {
        var visitor = new SearchFiles(this::checkFileName);
        try {
            Files.walkFileTree(dir, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.getPaths();
    }

    private static void checkArguments(ArgsName args) {
        var dir = Path.of(args.get("d"));
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException(String.format("'%s' is not a directory", dir));
        }
        args.get("n");
        var t = args.get("t");
        if (!List.of("name", "mask", "regex").contains(t)) {
            throw new IllegalArgumentException(String.format("Search type '%s' is not supported", t));
        }
        var out = Path.of(args.get("o"));
        if (Files.exists(out) && !Files.isRegularFile(out)) {
            throw new IllegalArgumentException(String.format("'%s' is not a regular file", out));
        }
    }

    public static void main(String[] args) {
        var arguments = ArgsName.of(args);
        checkArguments(arguments);
        var finder = new FileFinder(Path.of(arguments.get("d")), arguments.get("n"), arguments.get("t"));
        var found = finder.find();
        try {
            Files.write(Path.of(arguments.get("o")),
                    found.stream()
                            .map(Path::toString)
                            .toList(),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
