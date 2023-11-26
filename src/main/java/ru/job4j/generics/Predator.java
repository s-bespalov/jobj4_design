package ru.job4j.generics;

import java.util.StringJoiner;

public class Predator extends Animal {
    @Override
    public String toString() {
        return new StringJoiner(", ", Predator.class.getSimpleName() + "[", "]")
                .toString();
    }
}
