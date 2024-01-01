package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (var reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(line -> {
                if (!line.isEmpty() && !line.startsWith("#")) {
                    var eqIdx = line.indexOf('=');
                    if (eqIdx <= 0 || eqIdx == line.length() - 1) {
                        throw new IllegalArgumentException();
                    }
                    var key = line.substring(0, eqIdx);
                    var val = line.substring(eqIdx + 1);
                    values.put(key, val);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (var reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}