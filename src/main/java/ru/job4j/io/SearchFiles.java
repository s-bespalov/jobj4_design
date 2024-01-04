package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles implements FileVisitor<Path> {

    private Predicate<Path> condition;
    private List<Path> paths;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
        paths = new LinkedList<>();
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes basicFileAttributes) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes basicFileAttributes) throws IOException {
        if (condition.test(path)) {
            paths.add(path);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path,
                                           IOException e) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path,
                                              IOException e) throws IOException {
        return CONTINUE;
    }
}
