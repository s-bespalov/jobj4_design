package ru.job4j.generics;

import java.util.StringJoiner;

public class Tiger extends Predator {
    @Override
    public String toString() {
        return new StringJoiner(", ", Tiger.class.getSimpleName() + "[", "]")
                .toString();
    }
}
