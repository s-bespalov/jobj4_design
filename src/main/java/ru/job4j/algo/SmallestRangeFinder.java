package ru.job4j.algo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int[] rsl = null;
        for (int window = k; window <= nums.length; window++) {
            for (int i = 0; i <= nums.length - window; i++) {
                if (countUniqueNumbers(nums, i, i + window - 1) == k) {
                    rsl = new int[]{i, i + window - 1};
                    break;
                }
            }
            if (rsl != null) {
                break;
            }
        }
        return rsl;
    }

    public static int countUniqueNumbers(int[] nums, int start, int end) {
        return (int) IntStream.rangeClosed(start + 1, end).filter(i -> nums[i - 1] != nums[i]).count() + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}