package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private boolean[] tombstones;
    private final double correctLoadFactor;

    HashOpenAdressing() {
        this(2039);
    }

    @SuppressWarnings("unchecked")
    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = nextPrime(size);
        this.hashElems = (T[]) new Comparable[this.size];
        this.tombstones = new boolean[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil || (hashElems[hashId] == nil && tombstones[hashId])) {

            if (hashElems[hashId] != nil && hashElems[hashId].equals(elem))
                return hashElems[hashId];

            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        return null;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil || (hashElems[hashId] == nil && tombstones[hashId])) {

            if (hashElems[hashId].equals(elem)) {
                hashElems[hashId] = nil;
                tombstones[hashId] = true;
                nElems--;
                break;
            }

            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    @SuppressWarnings("unchecked")
    private void doubleResize() {
        int oldsize = this.size;
        this.size = nextPrime(this.size * 2);

        T[] oldHashElems = this.hashElems;
        this.hashElems = (T[]) new Comparable[this.size];
        this.tombstones = new boolean[this.size];
        this.nElems = 0;

        for (int i = 0; i < oldsize; i++) {
            if (oldHashElems[i] != nil)
                this.put(oldHashElems[i]);
        }
    }

    private int nextPrime(int s) {
        if (s <= 1)
            return 2;

        boolean prime = false;

        while (!prime) {
            prime = true;
            for (int i = 2; i * i <= s; i++) {
                if (s % i == 0) {
                    prime = false;
                    break;
                }
            }
            if (!prime)
                s++;
        }
        return s;
    }

    public void printHashTab() {
        int lim = this.size > 20 ? 20 : this.size;
        for (int i = 0; i < lim; i++) {
            if (hashElems[i] == null && !tombstones[i])
                System.out.println(i + " | nil");
            else if (hashElems[i] == null && tombstones[i])
                System.out.println(i + " | tombstone");
            else
                System.out.println(i + " | " + hashElems[i] + " | " + hashElems[i].hashCode());
        }
    }
}