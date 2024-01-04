package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, Set<String>> filesPaths = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        var key = new FileProperty(Files.size(file), file.getFileName().toString());
        if (!filesPaths.containsKey(key)) {
            filesPaths.put(key, new HashSet<>());
        }
        filesPaths.get(key).add(file.toAbsolutePath().toString());
        return super.visitFile(file, attributes);
    }

    public Map<FileProperty, Set<String>> getVisitedFiles() {
        return filesPaths;
    }
}