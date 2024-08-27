package ru.job4j.newcoll.tree;

import ru.job4j.collection.Queue;
import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        int[] count = {0};
        breadthWalk(root, v -> count[0]++);
        return count[0];
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        var result = new ArrayList<T>();
        breadthWalk(root, result::add);
        return result;
    }

    private void breadthWalk(Node<T> root, Consumer<T> consumer) {
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (queue.size() > 0) {
            var visit = queue.poll();
            consumer.accept(visit.getValue());
            visit.getChildren().forEach(queue::push);
        }
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        var result = false;
        var optionalChildNode = deepStream(root)
                .filter(n -> Objects.equals(n.getValue(), child))
                .findFirst();
        if (optionalChildNode.isEmpty()) {
            var optionalParentNode = deepStream(root)
                    .filter(n -> Objects.equals(n.getValue(), parent))
                    .findFirst();
            if (optionalParentNode.isPresent()) {
                var parentNode = optionalParentNode.get();
                parentNode.getChildren().add(new Node<>(child));
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        return deepStream(root)
                .filter(n -> Objects.equals(key, n.getValue()))
                .findFirst();
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        Optional<Node<T>> result = Optional.empty();
        if (Objects.equals(root.getValue(), key)) {
            result = Optional.of(root);
        } else {
            var optionalSiblings = deepStream(root)
                    .map(Node::getChildren)
                    .filter(c -> c.stream().anyMatch(n -> Objects.equals(n.getValue(), key)))
                    .findFirst();
            if (optionalSiblings.isPresent()) {
                var siblings = optionalSiblings.get();
                result = siblings.stream()
                        .filter(s -> Objects.equals(s.getValue(), key))
                        .findFirst();
                result.ifPresent(siblings::remove);
            }
        }
        return result;
    }


    private Stream<Node<T>> deepStream(Node<T> root) {
        return StreamSupport.stream(new DeepSpliterator<>(root), false);
    }

    private static class DeepSpliterator<T> implements Spliterator<Node<T>> {

        private final SimpleStack<Node<T>> stack = new SimpleStack<>();

        public DeepSpliterator(Node<T> root) {
            stack.push(root);
        }

        @Override
        public boolean tryAdvance(Consumer<? super Node<T>> consumer) {
            var rsl = false;
            if (stack.getSize() > 0) {
                var node = stack.pop();
                consumer.accept(node);
                node.getChildren().forEach(stack::push);
                rsl = true;
            }
            return rsl;
        }

        @Override
        public Spliterator<Node<T>> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return Long.MAX_VALUE;
        }

        @Override
        public int characteristics() {
            return ORDERED;
        }
    }
}