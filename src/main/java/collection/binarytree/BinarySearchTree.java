package collection.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        var dif = key.compareTo(node.key);
        if (dif < 0) {
            if (Objects.isNull(node.left)) {
                node.left = new Node(key);
                return true;
            } else {
                put(node.left, key);
            }
        } else if (dif > 0) {
            if (Objects.isNull(node.right)) {
                node.right = new Node(key);
                return true;
            } else {
                put(node.right, key);
            }
        }
        return false;
    }

    public boolean contains(T key) {
        return Objects.nonNull(find(root, key));
    }

    private Node find(Node node, T key) {
        if (Objects.nonNull(node)) {
            var dif = key.compareTo(node.key);
            if (dif == 0) {
                return node;
            } else if (dif < 0) {
                return find(node.left, key);
            } else {
                return find(node.right, key);
            }
        }
        return null;
    }

    public boolean remove(T key) {
        if (Objects.nonNull(key) && Objects.nonNull(root)) {
            return remove(root, key);
        }
        return false;
    }

    private boolean remove(Node source, T key) {
        boolean result = false;
        Node parent = null;
        boolean isLeftChild = false;
        var current = source;
        while (Objects.nonNull(current) && !result) {
            var dif = key.compareTo(current.key);
            if (dif > 0) {
                parent = current;
                isLeftChild = false;
                current = current.right;
            } else if (dif < 0) {
                parent = current;
                isLeftChild = true;
                current = current.left;
            } else {
                result = true;
            }
        }
        if (result) {
            Node replacement = extractReplacement(current);
            replace(replacement, current, parent, isLeftChild);
        }
        return result;
    }

    private void replace(Node replacement, Node destination, Node parent, boolean isLeft) {
        if (Objects.equals(root, destination)) {
            root = replacement;
        } else if (Objects.nonNull(parent)) {
            if (isLeft) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
        }
        if (Objects.nonNull(replacement)) {
            replacement.left = destination.left;
            replacement.right = destination.right;
        }
        destination.right = null;
        destination.left = null;
        destination.key = null;
    }

    private Node extractReplacement(Node nodeToReplace) {
        Node replacement = null;
        Node parent = nodeToReplace;
        var isLeftChild = false;
        if (Objects.nonNull(nodeToReplace.right)) {
            replacement = nodeToReplace.right;
            while (Objects.nonNull(replacement.left)) {
                parent = replacement;
                replacement = replacement.left;
                isLeftChild = true;
            }
        } else if (Objects.nonNull(nodeToReplace.left)) {
            replacement = nodeToReplace.left;
            isLeftChild = true;
            while (Objects.nonNull(replacement.right)) {
                parent = replacement;
                replacement = replacement.right;
                isLeftChild = false;
            }
        }
        if (Objects.nonNull(replacement)) {
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        return replacement;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        var results = new ArrayList<T>();
        return inPreOrder(root, results);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (Objects.nonNull(localRoot)) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        var results = new ArrayList<T>();
        return inPostOrder(root, results);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (Objects.nonNull(localRoot)) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        if (Objects.isNull(node.left)) {
            return node;
        } else {
            return minimum(node.left);
        }
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        if (Objects.isNull(node.right)) {
            return node;
        } else {
            return maximum(node.right);
        }
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}