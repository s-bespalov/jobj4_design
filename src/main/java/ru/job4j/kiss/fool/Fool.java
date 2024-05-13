package ru.job4j.kiss.fool;

import java.util.Objects;
import java.util.Scanner;

public class Fool {

    public static final String GAME_NAME = "Игра FizzBuzz.";
    public static final String ERROR = "Ошибка. Начинай снова.";

    public static String getFizzBuzz(int i) {
        var rsl = String.valueOf(i);
        var fizz = i % 3 == 0;
        var buzz = i % 5 == 0;
        if (fizz && buzz) {
            rsl = "FizzBuzz";
        } else if (fizz) {
            rsl = "Fizz";
        } else if (buzz) {
            rsl = "Buzz";
        }
        return rsl;
    }

    public static void main(String[] args) {
        System.out.println(GAME_NAME);
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(getFizzBuzz(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!Objects.equals(answer, getFizzBuzz(startAt))) {
                System.out.println(ERROR);
                startAt = 0;
            }
            startAt++;
        }
    }
}