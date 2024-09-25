package collection.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        var result = false;
        if (Objects.nonNull(key)) {
            var optionalNode = getNodeByKey(root, key);
            if (optionalNode.isPresent()) {
                var node = optionalNode.get();
                node.value = value;
            } else {
                root = insert(root, key, value);
            }
            result = true;
        }
        return result;
    }

    public boolean remove(T key) {
        var result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && containsKey(root, key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    public V get(T key) {
        var optionalResult = getNodeByKey(root, key);
        V result = null;
        if (optionalResult.isPresent()) {
            result = optionalResult.get().value;
        }
        return result;
    }

    public Set<T> keySet() {
        var set = new LinkedHashSet<T>();
        return keysInSymmetricalOrder(root, set);
    }

    public Collection<V> values() {
        var result = new ArrayList<V>();
        return valuesInSymmetricalOrder(root, result);
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Set<T> keysInSymmetricalOrder(Node localRoot, Set<T> set) {
        if (Objects.nonNull(localRoot)) {
            keysInSymmetricalOrder(localRoot.left, set);
            set.add(localRoot.key);
            keysInSymmetricalOrder(localRoot.right, set);
        }
        return set;
    }

    private List<V> valuesInSymmetricalOrder(Node localRoot, List<V> values) {
        if (Objects.nonNull(localRoot)) {
            valuesInSymmetricalOrder(localRoot.left, values);
            values.add(localRoot.value);
            valuesInSymmetricalOrder(localRoot.right, values);
        }
        return values;
    }

    private Optional<Node> getNodeByKey(Node localRoot, T key) {
        if (Objects.nonNull(localRoot)) {
            var diff = key.compareTo(localRoot.key);
            if (diff == 0) {
                return Optional.of(localRoot);
            } else if (diff < 0) {
                return getNodeByKey(localRoot.left, key);
            } else {
                return getNodeByKey(localRoot.right, key);
            }
        }
        return Optional.empty();
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        var newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        var newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private Node minimum(Node node) {
        if (Objects.isNull(node.left)) {
            return node;
        } else {
            return minimum(node.left);
        }
    }

    private Node maximum(Node node) {
        if (Objects.isNull(node.right)) {
            return node;
        } else {
            return maximum(node.right);
        }
    }

    private boolean containsKey(Node node, T key) {
        return getNodeByKey(node, key).isPresent();
    }

    private Node insert(Node localRoot, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(localRoot)) {
            int diff = key.compareTo(localRoot.key);
            if (diff < 0) {
                localRoot.left = insert(localRoot.left, key, value);
            } else {
                localRoot.right = insert(localRoot.right, key, value);
            }
            updateHeight(localRoot);
            result = balance(localRoot);
        }
        return result;
    }

}