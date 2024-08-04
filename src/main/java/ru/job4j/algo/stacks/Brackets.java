package ru.job4j.algo.stacks;

import java.util.ArrayDeque;
import java.util.Objects;

public class Brackets {

    private enum State {
        valid, insideBrackets, insideCurly, insideRound, invalid
    }

    public boolean isValid(String s) {
        var state = State.valid;
        var stateStack = new ArrayDeque<State>();
        for (var c : s.toCharArray()) {
            switch (Objects.requireNonNull(state)) {
                case valid -> {
                    if (c == '[') {
                        stateStack.push(state);
                        state = State.insideBrackets;
                    } else if (c == '{') {
                        stateStack.push(state);
                        state = State.insideCurly;
                    } else if (c == '(') {
                        stateStack.push(state);
                        state = State.insideRound;
                    } else if (c == ']' || c == '}' || c == ')') {
                        state = State.invalid;
                    }
                }
                case insideBrackets -> {
                    if (c == ']') {
                        state = stateStack.poll();
                    } else if (c == '{') {
                        stateStack.push(state);
                        state = State.insideCurly;
                    } else if (c == '[') {
                        stateStack.push(state);
                    } else if (c == '(') {
                        stateStack.push(state);
                        state = State.insideRound;
                    } else if (c == '}' || c == ')') {
                        state = State.invalid;
                    }
                }
                case insideCurly -> {
                    if (c == '}') {
                        state = stateStack.poll();
                    } else if (c == '{') {
                        stateStack.push(state);
                    } else if (c == '[') {
                        stateStack.push(state);
                        state = State.insideBrackets;
                    } else if (c == '(') {
                        stateStack.push(state);
                        state = State.insideRound;
                    } else if (c == ']' || c == ')') {
                        state = State.invalid;
                    }
                }
                case insideRound -> {
                    if (c == ')') {
                        state = stateStack.poll();
                    } else if (c == '{') {
                        stateStack.push(state);
                        state = State.insideCurly;
                    } else if (c == '[') {
                        stateStack.push(state);
                        state = State.insideBrackets;
                    } else if (c == '(') {
                        stateStack.push(state);
                    } else if (c == ']' || c == '}') {
                        state = State.invalid;
                    }
                }
                default -> {
                }
            }
        }
        return Objects.equals(state, State.valid);
    }
}
