package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    // for testing purposes only
    private int nOfRecursivePutCalls;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        validateParams(key, value);

        // for testing purposes only
        nOfRecursivePutCalls = 0;

        root = put(root, key, value);
        root.setColor(BLACK);
    }

    public void deleteMax() {
        if (root == null) {
            throw new IndexOutOfBoundsException("Tried to delete an element from empty tree.");
        }

        root = deleteMax(root);
        root.setColor(BLACK);
    }

    public String getInOrder() {
        if (this.root == null) {
            return null;
        }

        return traversePrint(this.root, "in").trim();
    }

    public String getPreOrder() {
        if (this.root == null) {
            return null;
        }

        return traversePrint(this.root, "pre").trim();
    }

    public String getPostOrder() {
        if (this.root == null) {
            return null;
        }

        return traversePrint(this.root, "post").trim();
    }

    private String traversePrint(Node<K, V> node, String type) {
        if (node == null) {
            throw new NullPointerException("Invalid node passed as a parameter!");
        }

        String result = "";
        String leftside = node.getLeft() != null ? traversePrint(node.getLeft(), type) : "";
        String rightside = node.getRight() != null ? traversePrint(node.getRight(), type) : "";

        if (type.equals("in")) {
            result = leftside + node.toString() + " " + rightside;
        } else if (type.equals("pre")) {
            result = node.toString() + " " + leftside + rightside;
        } else if (type.equals("post")) {
            result = leftside + rightside + node.toString() + " ";
        } else {
            throw new IllegalArgumentException("Invalid traversing type parameter!");
        }

        return result;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<K, V>(key, value);
        }

        // for testing purposes only
        nOfRecursivePutCalls++;

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (node == null) {
            throw new IndexOutOfBoundsException("Recursive deleteMax() should never receive null node as a parameter!");
        }

        if (isRed(node.getLeft())) {
            node = rotateRight(node);
        }

        if (node.getRight() == null) {
            return null;
        }

        if (!isRed(node.getRight()) && !isRed(node.getLeft())) {
            node.setColor(BLACK);
            node.getLeft().setColor(RED);
            node.getRight().setColor(RED);
            if (node.getLeft() != null && isRed(node.getLeft().getLeft())) {
                node = rotateRight(node);
                changeColors(node);
            }
        }

        node.setRight(deleteMax(node.getRight()));

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null ? false : node.isRed();
    }
}
