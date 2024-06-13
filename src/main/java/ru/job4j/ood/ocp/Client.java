package ru.job4j.ood.ocp;

public class Client {
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Client.name = name;
    }

    private static String name;
}
