package ru.job4j.ood.isp.example2;

public class Programmer implements Worker {
    @Override
    public void doProgramming() {
        System.out.println("Do programming");
    }

    @Override
    public void doDesign() {
        throw new RuntimeException("Not implemented exception");
    }

    @Override
    public void doTesting() {
        System.out.println("Do testing");
    }
}
