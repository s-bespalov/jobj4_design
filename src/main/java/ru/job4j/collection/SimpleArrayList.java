package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    private void grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public T set(int index, T newValue) {
        var oldValue = get(index);
        container[index] = newValue;
        return oldValue;

    }

    @Override
    public T remove(int index) {
        var value = get(index);
        if (index != size - 1) {
            System.arraycopy(container, index + 1, container, index, size - 1 - index);
        }
        container[--size] = null;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;
            final int exceptedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}