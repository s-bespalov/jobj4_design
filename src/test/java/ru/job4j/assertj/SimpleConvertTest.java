package ru.job4j.assertj;

import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class SimpleConvertTest {
    @Test
    void checkArray() {
        var simpleConvert = new SimpleConvert();
        var array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).isNotNull()
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        var simpleConvert = new SimpleConvert();
        var list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .hasSize(5)
                .contains("three")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("minus one", "twenty", "five")
                .doesNotContain("four", Index.atIndex(4));
    }

    @Test
    void checkSet() {
        var simpleConvert = new SimpleConvert();
        var set = simpleConvert.toSet("first", "second", "three", "four", "five", "first");
        assertThat(set).isNotNull()
                .hasSize(5)
                .areExactly(1, new Condition<>("first"::equals, "first"))
                .contains("first", "second", "three", "four", "five", "first");
    }

    @Test
    void checkMap() {
        var simpleConvert = new SimpleConvert();
        var map = simpleConvert.toMap("first", "second", "three", "four", "five", "first");
        assertThat(map).isNotNull()
                .hasSize(5)
                .contains(entry("first", 0), entry("second", 1), entry("three", 2), entry("four", 3), entry("five", 4))
                .doesNotContain(entry("first", 5))
                .containsKeys("first", "second", "three", "four", "five")
                .doesNotContainValue(5);
    }
}