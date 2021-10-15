package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            // System.out.println("Verbose debug: Null pointer received and fended off.");
            return;
        } else if (nums.length <= 1) {
            // System.out.println("Verbose debug: Array of length " + nums.length + " does not require sorting.");
            return; 
        }

        int n = nums.length;

        for (int i = 1; i < n; i++) {
            // System.out.println("Verbose debug: sorted " + (double)i*100/n + "% of the array.");
            double key = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > key) {
                nums[j+1] = nums[j--];
            }

            nums[j+1] = key;                   
        }
    }
}
