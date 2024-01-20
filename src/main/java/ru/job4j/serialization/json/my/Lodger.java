package ru.job4j.serialization.json.my;

import java.util.StringJoiner;

public class Lodger {
    private final String name;

    public Lodger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Lodger.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
