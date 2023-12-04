package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveOddNumbers() {
        var list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ListUtils.removeIf(list, x -> x % 2 != 0);
        assertThat(list).containsExactly(2, 4, 6, 8);
    }

    @Test
    void whenReplaceEvenWithZeros() {
        var list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ListUtils.replaceIf(list, x -> x % 2 == 0, 0);
        assertThat(list).containsExactly(1, 0, 3, 0, 5, 0, 7, 0);
    }

    @Test
    void whenRemoveAll() {
        var list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        var remove = Arrays.asList(1, 2, 3, 4);
        ListUtils.removeAll(list, remove);
        assertThat(list).containsExactly(5, 6, 7, 8);
    }
}