package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        var phrases = readPhrases();
        var log = new LinkedList<String>();
        var stopped = false;
        var input = new BufferedReader(new InputStreamReader(System.in));
        try {
            var line = input.readLine();
            log.add(line);
            var random = new Random();
            while (!Objects.equals(line, OUT)) {
                if (Objects.equals(line, STOP)) {
                    stopped = true;
                } else if (Objects.equals(line, CONTINUE)) {
                    stopped = false;
                }
                if (!stopped) {
                    var idx = random.nextInt(phrases.size());
                    System.out.println(phrases.get(idx));
                }
                line = input.readLine();
                log.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = List.of();
        try (var reader = new BufferedReader(new FileReader(botAnswers))) {
            phrases = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (var writer = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/botlog.txt", "data/bot_answers.txt");
        consoleChat.run();
    }
}
