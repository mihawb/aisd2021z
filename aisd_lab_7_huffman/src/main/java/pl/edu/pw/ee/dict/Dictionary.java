package pl.edu.pw.ee.dict;

import java.util.ArrayList;

public class Dictionary<K extends Comparable<K>, V> {
    private final RedBlackTree<K, V> tree;

    public <K, V> Dictionary() {
        tree = new RedBlackTree<>();
    }

    public void setValue(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Params (key, value) cannot be null.");
        }
        tree.put(key, value);
    }

    public V getValue(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get value by null key.");
        }
        return tree.get(key);
    }

    public ArrayList<V> getAllEntries() {
        return tree.getAll();
    }
}
