package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

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

        for (int i = 0; i < n-1; i++) {
            // System.out.println("Verbose debug: sorted " + (double)i*100/n + "% of the array.");
            int minValIndex = i+1;

            for (int j = i+2; j < n; j++) {
                minValIndex = nums[j] < nums[minValIndex] ? j : minValIndex;
            }

            if (nums[minValIndex] < nums[i]) {
                double temp = nums[minValIndex];
                nums[minValIndex] = nums[i];
                nums[i] = temp; 
            }
        }
    }

}
