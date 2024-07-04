package ru.job4j.ood.isp.example2;

public class Designer implements Worker {
    @Override
    public void doProgramming() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void doDesign() {
        System.out.println("Do design");
    }

    @Override
    public void doTesting() {
        throw new RuntimeException("Not implemented");
    }
}
