package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target, Path base) {
        try (var zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var path : sources) {
                var entryPath = base.relativize(path);
                var zipEntry = new ZipEntry(entryPath.toString());
                zip.putNextEntry(zipEntry);
                try (var input = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(input.readAllBytes());
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (var input = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(input.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkArguments(ArgsName args) {
        var root = Path.of(args.get("d"));
        if (!Files.exists(root)) {
            throw new IllegalArgumentException(String.format("Directory '%s' does not exist", root));
        }
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException(String.format("'%s' is not directory", root));
        }
        var e = args.get("e");
        if (e.length() < 2 || e.charAt(0) != '.') {
            throw new IllegalArgumentException(String.format("'%s' is not file extension", e));
        }
        var o = args.get("o");
        if (!o.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("The output file '%s' should have '.zip' extension", o));
        }
    }

    public static void main(String[] args) {
        var argsNames = ArgsName.of(args);
        checkArguments(argsNames);
        var root = Path.of(argsNames.get("d"));
        var target = Path.of(argsNames.get("o"));
        var exclude = argsNames.get("e");
        try {
            var files = Search.search(root, f -> !f.toString().endsWith(exclude));
            new Zip().packFiles(files, target.toFile(), root);
            System.out.println("done");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}