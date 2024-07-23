package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        var result = new int[left.length + right.length];
        var leftPtr = 0;
        var rightPtr = 0;
        for (var i = 0; i < result.length; i++) {
            if (leftPtr == left.length) {
                result[i] = right[rightPtr];
                rightPtr++;
            } else if (rightPtr == right.length) {
                result[i] = left[leftPtr];
                leftPtr++;
            } else if (right[rightPtr] < left[leftPtr]) {
                result[i] = right[rightPtr];
                rightPtr++;
            } else {
                result[i] = left[leftPtr];
                leftPtr++;
            }
        }
        return result;
    }
}
