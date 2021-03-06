package pl.edu.pw.ee.heap;

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;
    private int len;

    public MinHeap() {
        heap = new ArrayList<T>();
        len = 0;
    }

    public MinHeap(ArrayList<T> nums) {
        this();
        for (T elem : nums) {
            put(elem);
        }
    }

    public int getLength() {
        return len;
    }

    public ArrayList<T> getHeapArrayList() {
        return heap;
    }

    public void put(T item) {
        heap.add(item);
        len++;

        heapUp();
    }

    public T pop() {
        if (len <= 0) {
            throw new IndexOutOfBoundsException("Tried to pop from empty heap.");
        }

        len--;
        T result = heap.get(0);
        heap.remove(0);
        heapDown();
        return result;
    }

    private void heapUp() {
        int currentIndex = len - 1;
        int parentIndex = (currentIndex - 1) / 2;

        while (heap.get(parentIndex).compareTo(heap.get(currentIndex)) > 0 && currentIndex != parentIndex) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    private void heapDown() {
        int currentIndex = 0;
        int childIndex = currentIndex * 2 + 1;

        while (childIndex < len) {
            if (childIndex + 1 < len && heap.get(childIndex).compareTo(heap.get(childIndex + 1)) > 0)
                childIndex++;
            if (heap.get(currentIndex).compareTo(heap.get(childIndex)) <= 0)
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
}