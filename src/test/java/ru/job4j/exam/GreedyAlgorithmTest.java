package ru.job4j.exam;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class GreedyAlgorithmTest {

    @Test
    void whenAmountIsExactAndSufficientCoinsThenCorrectChange() {
        int[] coins = {10, 5, 2, 1};
        int[] coinsCount = {10, 5, 2, 1};
        List<Integer> expected = List.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 2, 1);
        List<Integer> actual = GreedyAlgorithm.getChange(93, coins, coinsCount);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenNotEnoughCoinsThenEmptyList() {
        int[] coins = {10, 5, 2, 1};
        int[] coinsCount = {10, 5, 2, 1};
        List<Integer> actual = GreedyAlgorithm.getChange(10000, coins, coinsCount);
        assertThat(actual).isEmpty();
    }

    @Test
    void whenAmountIsExactAndSufficientCoinsThenCorrectChange2() {
        int[] coins = {10, 5, 2, 1};
        int[] coinsCount = {10, 5, 2, 1};
        List<Integer> expected = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> expected.add(10));
        IntStream.range(0, 5).forEach(i -> expected.add(5));
        IntStream.range(0, 2).forEach(i -> expected.add(2));
        expected.add(1);
        List<Integer> actual = GreedyAlgorithm.getChange(130, coins, coinsCount);
        assertThat(actual).hasSameElementsAs(expected);
    }
}