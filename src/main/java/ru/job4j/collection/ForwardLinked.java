package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {

    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            var lastNode = getNodeAtIndex(size - 1);
            lastNode.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        var node = getNodeAtIndex(index);
        return node.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        var tmpNode = head;
        var deleted = tmpNode.item;
        head = head.next;
        tmpNode.next = null;
        tmpNode.item = null;
        size--;
        modCount++;
        return deleted;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> currentNode = head;
            final int exceptedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var element = currentNode.item;
                currentNode = currentNode.next;
                return element;
            }
        };
    }

    private Node<T> getNodeAtIndex(int index) {
        Objects.checkIndex(index, size);
        var node = head;
        while (index != 0) {
            node = node.next;
            index--;
        }
        return node;
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
