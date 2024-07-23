package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenEmptyArrayThenOk() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }

    @Test
    void whenSingleElementArrayThenOk() {
        int[] array = {5};
        assertThat(Merge.mergesort(array)).containsExactly(5);
    }

    @Test
    void whenArrayWithDuplicatesThenOk() {
        int[] array = {5, 3, 8, 3, 1, 5, 9, 3};
        assertThat(Merge.mergesort(array)).containsExactly(1, 3, 3, 3, 5, 5, 8, 9);
    }

    @Test
    void whenArrayWithNegativeNumbersThenOk() {
        int[] array = {-3, -1, -7, -5, -2, -8, 0};
        assertThat(Merge.mergesort(array)).containsExactly(-8, -7, -5, -3, -2, -1, 0);
    }

    @Test
    void whenArrayAlreadySortedThenOk() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(Merge.mergesort(array)).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
