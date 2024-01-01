package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void whenUnavailableExample1(@TempDir Path tempDir) throws IOException {
        var source = tempDir.resolve("server.log").toFile();
        var target = tempDir.resolve("target.csv").toFile();
        var sep = System.lineSeparator();
        try (var output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        var rsl = new StringBuilder();
        try (var input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> rsl.append(line).append(sep));
        }
        var expected = "10:57:01;10:59:01;" + sep + "11:01:02;11:02:02;" + sep;
        assertThat(rsl.toString()).isEqualTo(expected);
    }

    @Test
    void whenUnavailableExample2(@TempDir Path tempDir) throws IOException {
        var source = tempDir.resolve("server.log").toFile();
        var target = tempDir.resolve("target.csv").toFile();
        var sep = System.lineSeparator();
        try (var output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("300 11:02:02");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        var rsl = new StringBuilder();
        try (var input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> rsl.append(line).append(sep));
        }
        var expected = "10:57:01;11:02:02;" + sep;
        assertThat(rsl.toString()).isEqualTo(expected);
    }
}