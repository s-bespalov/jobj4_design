package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComments() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenWrongTemplate() {
        String path = "./data/wrong_template.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev=");
        assertThat(config.value("name2")).isEqualTo("Petr Arsentev=1");
    }

    @Test
    void whenWrongTemplateThrowsException() {
        String path = "./data/wrong_template_throws_exception.properties";
        assertThatThrownBy(() -> {
            var config = new Config(path);
            config.load();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongTemplateThrowsException2() {
        String path = "./data/wrong_template_throws_exception2.properties";
        assertThatThrownBy(() -> {
            var config = new Config(path);
            config.load();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongTemplateThrowsException3() {
        String path = "./data/wrong_template_throws_exception3.properties";
        assertThatThrownBy(() -> {
            var config = new Config(path);
            config.load();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}