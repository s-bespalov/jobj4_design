package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (var input = new BufferedReader(new FileReader(source));
             var writer = new BufferedWriter(new FileWriter(target))) {
            var lineList = input.lines().toList();
            var available = true;
            for (var line : lineList) {
                var words = line.split(" ");
                var status = Integer.parseInt(words[0]);
                if (available && (status >= 400)) {
                    available = false;
                    writer.append(words[1]).append(";");
                } else if (!available && status < 400) {
                    available = true;
                    writer.append(words[1]).append(";").append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}