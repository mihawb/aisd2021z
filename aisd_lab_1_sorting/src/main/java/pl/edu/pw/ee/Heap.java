package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {
    private ArrayList<T> heap;
    private int n;

    public Heap() {
        heap = new ArrayList<T>();
        n = 0;
    }

    public void put(T item) {
        heap.add(item);
        n++;
        
        heapUp();
    }

    public T pop() {
        if (n <= 0) {
            throw new IndexOutOfBoundsException("Tried to pop from empty heap."); 
        }
        swap(heap, 0, (n--)-1);

        heapDown();

        printheap();
        System.out.println(n);
        return heap.get(n);
    }

    public void heapUp() {
        int currentIndex = n - 1;
        int parentIndex = (currentIndex - 1) / 2;

        while (heap.get(parentIndex).compareTo(heap.get(currentIndex)) < 0 && currentIndex != parentIndex) {
            swap(heap, parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    public void heapDown() {
        int currentIndex = 0;
        int childIndex = currentIndex * 2 + 1;

        while (childIndex < n) {
            if (childIndex + 1 < n && heap.get(childIndex).compareTo(heap.get(childIndex + 1)) < 0)
                childIndex++;
            if (heap.get(currentIndex).compareTo(heap.get(childIndex)) >= 0)
                break;
            
            swap(heap, currentIndex, childIndex);
            currentIndex = childIndex;
            childIndex = currentIndex * 2 + 1;
        }
    }

    private void swap(ArrayList<T> data, int firstId, int secondId) {
        if (firstId != secondId) {
            T firstValue = data.get(firstId);
            data.set(firstId, data.get(secondId));
            data.set(secondId, firstValue);
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