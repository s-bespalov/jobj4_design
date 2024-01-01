package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void whenUnavailableExample1() {
        var in = "./data/server_example1.log";
        var out = "./data/unavailable_example1.csv";
        var sep = System.lineSeparator();
        new Analysis().unavailable(in, out);
        var rsl = new StringBuilder();
        try (var input = new BufferedReader(new FileReader(out))) {
            input.lines().forEach(line -> rsl.append(line).append(sep));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var expected = "10:57:01;10:59:01;" + sep + "11:01:02;11:02:02;" + sep;
        assertThat(rsl.toString()).isEqualTo(expected);
    }

    @Test
    void whenUnavailableExample2() {
        var in = "./data/server_example2.log";
        var out = "./data/unavailable_example2.csv";
        var sep = System.lineSeparator();
        new Analysis().unavailable(in, out);
        var rsl = new StringBuilder();
        try (var input = new BufferedReader(new FileReader(out))) {
            input.lines().forEach(line -> rsl.append(line).append(sep));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var expected = "10:57:01;11:02:02;" + sep;
        assertThat(rsl.toString()).isEqualTo(expected);
    }
}