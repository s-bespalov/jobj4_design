package ru.job4j.algo.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        var rsl = new int[intervals.length][2];
        IntStream.range(0, rsl.length).forEach(i -> {
            rsl[i][0] = intervals[i][0];
            rsl[i][1] = intervals[i][1];
        });
        Arrays.sort(rsl, Comparator.comparingInt(x -> x[0]));
        var newLength = rsl.length;
        var i = 0;
        var j = 1;
        var k = 0;
        while (k < rsl.length) {
            while (j < rsl.length && rsl[k][1] >= rsl[j][0]) {
                if (rsl[k][1] < rsl[j][1]) {
                    rsl[k][1] = rsl[j][1];
                }
                j++;
                newLength--;
            }
            rsl[i] = rsl[k];
            k = j;
            i++;
            j++;
        }
        return Arrays.copyOf(rsl, newLength);
    }
}
