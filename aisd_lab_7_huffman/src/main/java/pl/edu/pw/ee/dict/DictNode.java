package pl.edu.pw.ee.dict;

import static pl.edu.pw.ee.dict.Color.RED;

public class DictNode<K extends Comparable<K>, V> {

    private K key;
    private V value;
    private DictNode<K, V> left, right;
    private Color color;

    public DictNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.color = RED;
    }

    @Override
    public String toString() {
        return this.key + ":" + this.value;
    }

    public boolean isRed() {
        return RED.equals(color);
    }

    public K getKey() {
        return key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public DictNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(DictNode<K, V> leftNode) {
        left = leftNode;
    }

    public DictNode<K, V> getRight() {
        return right;
    }

    public void setRight(DictNode<K, V> rightNode) {
        right = rightNode;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
