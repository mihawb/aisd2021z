package pl.edu.pw.ee;

import javax.lang.model.util.ElementScanner14;

import pl.edu.pw.ee.exceptions.NotImplementedException;
import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private boolean[] tombstones;
    private final double correctLoadFactor;

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.tombstones = new boolean[this.size]; //defaults to false
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

    private void doubleResize() {
        T[] oldHashElems = this.hashElems;
        this.hashElems = (T[]) new Comparable[this.size * 2];
        this.tombstones = new boolean[this.size * 2];

        for (int i = 0; i < this.size; i++) {
            if (oldHashElems[i] != nil)
                this.put(oldHashElems[i]);
        }

        this.size *= 2;
    }

    public void printhashtab() {
        int lim = this.size > 25 ? 25 : this.size;
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