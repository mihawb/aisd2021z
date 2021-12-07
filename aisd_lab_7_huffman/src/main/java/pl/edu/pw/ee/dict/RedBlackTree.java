package pl.edu.pw.ee.dict;

import static pl.edu.pw.ee.dict.Color.BLACK;
import static pl.edu.pw.ee.dict.Color.RED;
import java.util.ArrayList;

public class RedBlackTree<K extends Comparable<K>, V> {

    private DictNode<K, V> root;

    public V get(K key) {
        validateKey(key);
        DictNode<K, V> node = root;

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

        root = put(root, key, value);
        root.setColor(BLACK);
    }

    public ArrayList<V> getAll() {
        ArrayList<V> entries = new ArrayList<>();

        aquireAllEntries(this.root, entries);

        return entries;
    }

    private void aquireAllEntries(DictNode<K, V> n, ArrayList<V> entries) {
        if (n != null) {
            entries.add(n.getValue());
            aquireAllEntries(n.getLeft(), entries);
            aquireAllEntries(n.getRight(), entries);
        }
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, DictNode<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, DictNode<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private DictNode<K, V> put(DictNode<K, V> node, K key, V value) {
        if (node == null) {
            return new DictNode<K, V>(key, value);
        }

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

    private boolean isKeyBiggerThanNode(K key, DictNode<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(DictNode<K, V> node, K key, V value) {
        DictNode<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, DictNode<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(DictNode<K, V> node, K key, V value) {
        DictNode<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private DictNode<K, V> reorganizeTree(DictNode<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private DictNode<K, V> rotateLeftIfNeeded(DictNode<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private DictNode<K, V> rotateLeft(DictNode<K, V> node) {
        DictNode<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private DictNode<K, V> rotateRightIfNeeded(DictNode<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private DictNode<K, V> rotateRight(DictNode<K, V> node) {
        DictNode<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(DictNode<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(DictNode<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(DictNode<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(DictNode<K, V> node) {
        return node == null ? false : node.isRed();
    }
}
