package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        var nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenGetMapA1B2ThenA1B2() {
        var nameLoad = new NameLoad();
        String[] input = {"a=1", "b=2"};
        nameLoad.parse(input);
        assertThat(nameLoad.getMap())
                .containsAllEntriesOf(Map.of("a", "1", "b", "2"))
                .doesNotContainKeys("1", "2");
    }

    @Test
    void whenGetMapA1B2B5ThenA1B2Plus5() {
        var nameLoad = new NameLoad();
        String[] input = {"a=1", "b=2", "b=5"};
        nameLoad.parse(input);
        assertThat(nameLoad.getMap())
                .doesNotContainKeys("1", "2", "5")
                .doesNotContainValue("5")
                .doesNotContainValue("2")
                .containsAllEntriesOf(Map.of("a", "1", "b", "2+5"));
    }

    @Test
    void checkDoesNotContainTheSymbolEquals() {
        var nameLoad = new NameLoad();
        String[] input = {"a=1", "b2"};
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("does not contain the symbol '='")
                .hasMessageContaining("b2");
    }

    @Test
    void checkDoesNotContainAKey() {
        var nameLoad = new NameLoad();
        String[] input = {"a=1", "=2"};
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining("=2");
    }

    @Test
    void checkDoesNotContainAValue() {
        var nameLoad = new NameLoad();
        String[] input = {"a=1", "b="};
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining("b=");
    }
}