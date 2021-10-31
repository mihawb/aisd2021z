package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;
import java.util.List;
import java.util.ArrayList;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private List<Elem> hashElems;
    private int nElem;

    private class Elem {
        private T value;
        private Elem next;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new ArrayList<Elem>(size);
        initializeHash(size);
    }

    @Override
    public void add(T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null!");
            
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems.get(hashId);

        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }

        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems.set(hashId, new Elem(value, hashElems.get(hashId)));
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null!");

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null!");

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);
        
        // checking first element of linked list
        if (elem != nil && elem.value.equals(value)) {
            hashElems.set(hashId, elem.next);
            return;
        }

        //iterating thru the rest of linked list
        while (elem != nil && elem.next != nil && !elem.next.value.equals(value)) {
            elem = elem.next;
        }

        // if the while loop stopped with elem.next == nil,
        // the node to delete hasnt been found
        // and the hash table doesnt need to be altered
        if (elem != nil && elem.next != nil)
            elem.next = elem.next.next;
    }

    public double countLoadFactor() {
        double size = hashElems.size();
        return nElem / size;
    }

    private void initializeHash(int size) {
        while (hashElems.size() < size) hashElems.add(nil);
    }

    private int countHashId(int hashCode) {
        int n = hashElems.size();
        return Math.abs(hashCode) % n;
    }
}