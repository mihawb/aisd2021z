package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {
    private ArrayList<T> heap;

    public Heap() {
        heap = new ArrayList<T>();
    }

    public void put(T item) {
        heap.add(item);
        
        heapUp();
    }

    public T pop() {
        if (heap.size() <= 0) {
            throw new IndexOutOfBoundsException("Tried to pop from empty heap."); 
        }
        swap(0, heap.size() - 1);        

        heapDown();

        printheap();
        return heap.remove(heap.size() - 1);
    }

    public void heapUp() {
        int currentIndex = heap.size() - 1;
        int parentIndex = (currentIndex - 1) / 2;

        while (heap.get(parentIndex).compareTo(heap.get(currentIndex)) < 0 && currentIndex != parentIndex) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    public void heapDown() {
        int currentIndex = 0;
        int childIndex = currentIndex * 2 + 1;

        while (childIndex < heap.size() - 1) {
            if (childIndex + 1 < heap.size() - 1 && heap.get(childIndex).compareTo(heap.get(childIndex + 1)) < 0)
                childIndex++;
            if (heap.get(currentIndex).compareTo(heap.get(childIndex)) >= 0)
                break;
            
            swap(currentIndex, childIndex);
            currentIndex = childIndex;
            childIndex = currentIndex * 2 + 1;
        }
    }

    private void swap(int firstId, int secondId) {
        if (firstId != secondId) {
            T firstValue = heap.get(firstId);
            heap.set(firstId, heap.get(secondId));
            heap.set(secondId, firstValue);
        }
    }

    public void printheap() {
        System.out.printf("[ ");
        for (T elem : heap) {
            System.out.printf("%.1f ", elem);
        }
        System.out.printf("]%n");
    }
}