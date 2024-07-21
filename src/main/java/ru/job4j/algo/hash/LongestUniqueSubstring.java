package ru.job4j.algo.hash;

import java.util.HashMap;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        var rsl = "";
        var window = str.length();
        while (window > 0) {
            for (int i = 0; i <= str.length() - window; i++) {
                var substring = str.substring(i, i + window);
                if (isUniqString(substring)) {
                    rsl = substring;
                    break;
                }
            }
            if (!rsl.isEmpty()) {
                break;
            }
            window--;
        }
        return rsl;
    }

    private static boolean isUniqString(String str) {
        var histogram = new HashMap<Integer, Integer>();
        str.codePoints().forEach(cp -> {
            histogram.computeIfPresent(cp, (k, v) -> v + 1);
            histogram.putIfAbsent(cp, 1);
        });
        return histogram.entrySet().stream().filter(entry -> entry.getValue() > 1).findAny().isEmpty();
    }
}