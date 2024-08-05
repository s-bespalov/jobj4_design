package ru.job4j.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class SimplifyPath {
    public String simplifyPath(String path) {
        var stack = new ArrayDeque<String>();
        Arrays.stream(path.split("/")).forEach(x -> {
            if (!x.isEmpty() && !Objects.equals(x, ".")) {
                if (Objects.equals(x, "..")) {
                    if (!stack.isEmpty()) {
                        stack.poll();
                    }
                } else {
                    stack.push(x);
                }
            }
        });
        var joiner = new StringJoiner("/", "/", "");
        IntStream.range(0, stack.size())
                .forEach(i -> joiner.add(stack.pollLast()));
        return joiner.toString();
    }
}
