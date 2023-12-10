package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75F;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (LOAD_FACTOR - (count / (float) capacity) <= 0.01F) {
            expand();
        }
        var rsl = false;
        var h = Objects.hashCode(key);
        var i = indexFor(hash(h));
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
        var newTable = new MapEntry[capacity];
        Arrays.stream(table).forEach(entry -> {
            if (entry != null) {
                var h = Objects.hashCode(entry.key);
                var i = indexFor(hash(h));
                if (newTable[i] == null) {
                    newTable[i] = entry;
                }
            }
        });
        table = newTable;
    }

    @Override
    public V get(K key) {
        var h = Objects.hashCode(key);
        var i = indexFor(hash(h));
        V rsl = null;
        if (table[i] != null
                && Objects.hashCode(table[i].key) == h
                && Objects.equals(key, table[i].key)) {
            rsl = table[i].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        var h = Objects.hashCode(key);
        var i = indexFor(hash(h));
        var rsl = false;
        if (table[i] != null
                && Objects.hashCode(table[i].key) == h
                && Objects.equals(key, table[i].key)) {
            table[i] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        var map = new NonCollisionMap<Integer, String>();
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
    }
}