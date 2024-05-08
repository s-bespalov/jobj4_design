package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        var reference = new SoftReference<V>(value);
        cache.put(key, reference);
    }

    public final V get(K key) {
        V value = null;
        if (cache.containsKey(key)) {
            var reference = cache.get(key);
            value = reference.get();
        }
        if (value == null) {
            value = load(key);
        }
        return value;
    }

    protected abstract V load(K key);

}