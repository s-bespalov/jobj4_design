package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    public static String getCorrectPCAnswers() {
        return generateCorrectAnswers(1, 100);
    }

    public static String getCorrectPlayerAnswers() {
        return generateCorrectAnswers(2, 100);
    }

    public static String generateCorrectAnswers(int seed, int end) {
        var sb = new StringBuilder();
        IntStream.iterate(seed, i -> i <= end, i -> i + 2)
                .forEach(i -> {
                    sb.append(Fool.getFizzBuzz(i));
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Test
    public void mainWhenAllAnswersAreCorrect() {
        var actualOutput = new ByteArrayOutputStream();
        var input = getCorrectPlayerAnswers();
        var excepted = Fool.GAME_NAME + System.lineSeparator() + getCorrectPCAnswers();
        System.setOut(new PrintStream(actualOutput));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Fool.main(null);
        assertThat(actualOutput.toString()).isEqualTo(excepted);
    }

    @Test
    public void mainWhenWrongFizzAt6() {
        var actualOutput = new ByteArrayOutputStream();
        var input = generateCorrectAnswers(2, 4)
                + "6" + System.lineSeparator()
                + getCorrectPlayerAnswers();
        var excepted = Fool.GAME_NAME + System.lineSeparator()
                + generateCorrectAnswers(1, 5)
                + Fool.ERROR + System.lineSeparator()
                + getCorrectPCAnswers();
        System.setOut(new PrintStream(actualOutput));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Fool.main(null);
        assertThat(actualOutput.toString()).isEqualTo(excepted);
    }

    @Test
    public void mainWhenWrongBuzzAt10() {
        var actualOutput = new ByteArrayOutputStream();
        var input = generateCorrectAnswers(2, 8)
                + "10" + System.lineSeparator()
                + getCorrectPlayerAnswers();
        var excepted = Fool.GAME_NAME + System.lineSeparator()
                + generateCorrectAnswers(1, 9)
                + Fool.ERROR + System.lineSeparator()
                + getCorrectPCAnswers();
        System.setOut(new PrintStream(actualOutput));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Fool.main(null);
        assertThat(actualOutput.toString()).isEqualTo(excepted);
    }

    @Test
    public void mainWhenWrongFizzBuzzAt30() {
        var actualOutput = new ByteArrayOutputStream();
        var input = generateCorrectAnswers(2, 28)
                + "30" + System.lineSeparator()
                + getCorrectPlayerAnswers();
        var excepted = Fool.GAME_NAME + System.lineSeparator()
                + generateCorrectAnswers(1, 29)
                + Fool.ERROR + System.lineSeparator()
                + getCorrectPCAnswers();
        System.setOut(new PrintStream(actualOutput));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Fool.main(null);
        assertThat(actualOutput.toString()).isEqualTo(excepted);
    }

    @Test
    public void when2ThenFizzBuzz2() {
        assertThat(Fool.getFizzBuzz(2)).isEqualTo("2");
    }

    @Test
    public void when3ThenFizzBuzzFizz() {
        assertThat(Fool.getFizzBuzz(3)).isEqualTo("Fizz");
    }

    @Test
    public void when30ThenFizzBuzzFizzBuzz() {
        assertThat(Fool.getFizzBuzz(30)).isEqualTo("FizzBuzz");
    }

    @Test
    public void when10ThenFizzBuzzBuzz() {
        assertThat(Fool.getFizzBuzz(10)).isEqualTo("Buzz");
    }
}