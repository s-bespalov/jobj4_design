package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Multiple {
    public static void main(String[] args) {
        try (var output = new FileOutputStream("data" + File.separator + "multiple.txt")) {
            for (int i = 1; i <= 9; i++) {
                output.write(String
                        .format("%d * %d = %d%s", 1, i, 1 * i, System.lineSeparator())
                        .getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
