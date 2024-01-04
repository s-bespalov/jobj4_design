package ru.job4j.io.duplicates;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

public class DuplicatesFinder {

    private static class FormatSize {

        private static final long BYTE = 1L;
        private static final long KB = BYTE * 1000;
        private static final long MB = KB * 1000;
        private static final long GB = MB * 1000;
        private static final long TB = GB * 1000;
        private static final long PB = TB * 1000;
        private static final long EB = PB * 1000;

        private static final DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");

        private static String formatSize(long size, long divider, String unitName) {
            return DEC_FORMAT.format((double) size / divider) + " " + unitName;
        }

        public static String toHumanReadableSIPrefixes(long size) {
            if (size < 0) {
                throw new IllegalArgumentException("Invalid file size: " + size);
            }
            if (size >= EB) {
                return formatSize(size, EB, "EB");
            }
            if (size >= PB) {
                return formatSize(size, PB, "PB");
            }
            if (size >= TB) {
                return formatSize(size, TB, "TB");
            }
            if (size >= GB) {
                return formatSize(size, GB, "GB");
            }
            if (size >= MB) {
                return formatSize(size, MB, "MB");
            }
            if (size >= KB) {
                return formatSize(size, KB, "KB");
            }
            return formatSize(size, BYTE, "Bytes");
        }
    }

    public static void printDuplicates(PrintStream output, Map<FileProperty, Set<String>> filesPaths) {
        filesPaths.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e -> {
                    var f = e.getKey();
                    var dirs = e.getValue();
                    output.printf("%s - %s%n",
                            f.getName(),
                            FormatSize.toHumanReadableSIPrefixes(f.getSize()));
                    dirs.forEach(d -> output.println("\t" + d));
                });
    }

    public static void main(String[] args) throws IOException {
        var visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        printDuplicates(System.out, visitor.getVisitedFiles());
    }
}