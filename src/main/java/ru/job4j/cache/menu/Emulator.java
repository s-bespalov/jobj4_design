package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {

    private static final String MENU = """
            1 - Указать кешируемую директорию
            2 - Загрузить содержимое файла в кэш
            3 - Получить содержимое из файла кэша
            Другое - Выход
            """;
    public static final int SET_CACHE_DIR = 1;
    public static final int LOAD_FILE_INTO_CACHE = 2;
    public static final int LOAD_FILE_FROM_CACHE = 3;
    public static final String SELECT_DIR = "Введите путь к директории";
    public static final String SELECT_FILE = "Введите имя файла";
    public static final String CACHE_DIR_NOT_SET = "Кешируемая директория не выбрана";
    public static final String CONTENT = "Содержимое файла:";

    public static void main(String[] args) throws IOException {
        DirFileCache cacheDir = null;
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            switch (userChoice) {
                case SET_CACHE_DIR -> {
                    System.out.println(SELECT_DIR);
                    var dir = scanner.nextLine();
                    if (!Files.isDirectory(Path.of(dir))) {
                        throw new IllegalArgumentException(String.format("%s is not a directory", dir));
                    }
                    cacheDir = new DirFileCache(dir);
                }
                case LOAD_FILE_INTO_CACHE -> {
                    if (cacheDir == null) {
                        System.out.println(CACHE_DIR_NOT_SET);
                    } else {
                        System.out.println(SELECT_FILE);
                        var file = scanner.nextLine();
                        if (!Files.isRegularFile(Path.of(cacheDir.getCachingDir(), file))) {
                            throw new IllegalArgumentException(String.format("%s is not a regular file", file));
                        }
                        cacheDir.put(file);
                    }
                }
                case LOAD_FILE_FROM_CACHE -> {
                    if (cacheDir == null) {
                        System.out.println(CACHE_DIR_NOT_SET);
                    } else {
                        System.out.println(SELECT_FILE);
                        var file = scanner.nextLine();
                        if (!Files.isRegularFile(Path.of(cacheDir.getCachingDir(), file))) {
                            throw new IllegalArgumentException(String.format("%s is not a regular file", file));
                        }
                        var content = cacheDir.get(file);
                        System.out.println(CONTENT);
                        System.out.println(content);
                    }
                }
                default -> {
                    run = false;
                }
            }
        }
    }
}
