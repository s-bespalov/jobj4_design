package ru.job4j.serialization.json.my;

import java.util.Arrays;
import java.util.StringJoiner;

public class Apartment {

    private final int number;
    private final Lodger[] lodgers;

    public int getNumber() {
        return number;
    }

    public Lodger[] getLodgers() {
        return lodgers;
    }

    public Apartment(int number, Lodger[] lodgers) {
        this.number = number;
        this.lodgers = lodgers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Apartment.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("lodgers=" + Arrays.toString(lodgers))
                .toString();
    }
}
