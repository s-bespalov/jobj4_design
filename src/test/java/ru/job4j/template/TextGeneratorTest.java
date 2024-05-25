package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class TextGeneratorTest {

    @Test
    public void whenTemplateWithNameAndSubject() {
        var template = "I am a ${name}, Who are ${subject}? ";
        var args = Map.of("name", "Sergey Bespalov", "subject", "you");
        var excepted = "I am a Sergey Bespalov, Who are you? ";
        var generator = new TextGenerator();
        var actual = generator.produce(template, args);
        assertThat(actual).isEqualTo(excepted);
    }

    @Test
    public void whenTemplateHasExtraKeysThrowException() {
        var template = "Do ${subject} like ${subject2}? ";
        var args = Map.of("subject", "you");
        var generator = new TextGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The key \"subject2\" is not included in arguments");
    }

    @Test
    public void whenArgumentsHaveExtraKeyThrowException() {
        var template = "Do ${subject} like ${subject2}? ";
        var args = Map.of("subject", "you", "subject2", "fish", "name", "Sergey");
        var generator = new TextGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("An extra key \"name\" is presented in arguments");
    }
}