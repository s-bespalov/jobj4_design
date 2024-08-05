package ru.job4j.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimplifyPathTest {

    @Test
    public void whenHome() {
        var home = "/home/";
        var actual = new SimplifyPath().simplifyPath(home);
        var excepted = "/home";
        assertThat(actual).isEqualTo(excepted);
    }

    @Test
    public void whenFooHome() {
        var home = "/home//foo/";
        var actual = new SimplifyPath().simplifyPath(home);
        var excepted = "/home/foo";
        assertThat(actual).isEqualTo(excepted);
    }

}