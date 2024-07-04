package ru.job4j.ood.isp.example;

public class Fax implements Machine {
    @Override
    public void print(String content) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String scan() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void fax(String content) {
        System.out.print("Do something");
    }
}
