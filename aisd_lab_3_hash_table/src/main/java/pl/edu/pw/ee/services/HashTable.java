package pl.edu.pw.ee.services;

public interface HashTable<T extends Comparable<T>> {

    void add(T value);

    Object get(T value);

    void delete(T value);
}