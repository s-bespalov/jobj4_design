package ru.job4j.algo.greedy;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        var rsl = -1;
        var start = 0;
        var totalGas = 0;
        var totalCost = 0;
        var tank = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (totalGas >= totalCost) {
            rsl = start;
        }
        return rsl;
    }
}
