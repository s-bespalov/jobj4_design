package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public String getCachingDir() {
        return cachingDir;
    }

    public void put(String file) {
        var content = load(file);
        this.put(file, content);
    }

    @Override
    protected String load(String key) {
        String result = null;
        var filePath = Path.of(cachingDir, key);
        try {
            result = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}