package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

import java.util.ArrayList;

public class HeapSort implements Sorting{
    public void sort(double [] nums) {
        ArrayList<Double> temp = new ArrayList<Double>();
        for (double num : nums) {
            temp.add(num);
        }
        // omitting the use of an adapter is not feasible since
        // Sorting and HeapInterface use incompatible data types
        // i.e. primitive double array and ArrayList of Comparable class items 

        // actual sorting
        Heap<Double> heapobj = new Heap<Double>(temp);
        int n = heapobj.getLength();
        for (int i = 0; i < n; i++) {
            heapobj.pop();
        }

        // same deal; unable to just return the ArrayList, have to convert it
        for (int i = 0; i < n; i++) {
            nums[i] = heapobj.getHeapArrayList().get(i);
        }
    }
}
