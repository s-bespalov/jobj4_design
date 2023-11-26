package ru.job4j.generics;

import java.util.StringJoiner;

public class Animal {
    @Override
    public String toString() {
        return new StringJoiner(", ", Animal.class.getSimpleName() + "[", "]")
                .toString();
    }
}
