package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;

public class GreedyAlgorithm {
    public static List<Integer> getChange(int amount, int[] coins, int[] coinsCount) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            int spent = 0;
            while (amount >= coins[i] && spent < coinsCount[i]) {
                amount -= coins[i];
                result.add(coins[i]);
                spent++;
            }
        }
        if (amount > 0) {
            result = List.of();
        }
        return result;
    }
}
