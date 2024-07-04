package ru.job4j.ood.isp.example;


public class Printer implements Machine {
    @Override
    public void print(String content) {
        System.out.print("Do something");
    }

    @Override
    public String scan() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void fax(String content) {
        throw new RuntimeException("Not implemented");
    }
}
