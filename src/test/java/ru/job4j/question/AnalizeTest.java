package ru.job4j.question;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalizeTest {

    @Test
    void whenNotChanged() {
        var u1 = new User(1, "A");
        var u2 = new User(2, "B");
        var u3 = new User(3, "C");
        var previous = Set.of(u1, u2, u3);
        var current = Set.of(u1, u2, u3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 0, 0));
    }

    @Test
    void whenOneChanged() {
        var u1 = new User(1, "A");
        var u2 = new User(2, "B");
        var u3 = new User(3, "C");
        var previous = Set.of(u1, u2, u3);
        var current = Set.of(u1, new User(2, "BB"), u3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 1, 0));
    }

    @Test
    void whenOneDeleted() {
        var u1 = new User(1, "A");
        var u2 = new User(2, "B");
        var u3 = new User(3, "C");
        var previous = Set.of(u1, u2, u3);
        var current = Set.of(u1, u3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 0, 1));
    }

    @Test
    void whenOneAdded() {
        var u1 = new User(1, "A");
        var u2 = new User(2, "B");
        var u3 = new User(3, "C");
        var previous = Set.of(u1, u2, u3);
        var current = Set.of(u1, u2, u3, new User(4, "D"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(1, 0, 0));
    }

    @Test
    void whenAllChanged() {
        var u1 = new User(1, "A");
        var u2 = new User(2, "B");
        var u3 = new User(3, "C");
        var previous = Set.of(u1, u2, u3);
        var current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(1, 1, 1));
    }
}