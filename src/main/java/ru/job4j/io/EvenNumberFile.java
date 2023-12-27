package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (var input = new FileInputStream("data" + File.separator + "even.txt")) {
            var lines = new String(input.readAllBytes()).split(System.lineSeparator());
            for (var line : lines) {
                System.out.printf("%s is %s\n", line, Integer.parseInt(line) % 2 == 0 ? "even" : "odd");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
