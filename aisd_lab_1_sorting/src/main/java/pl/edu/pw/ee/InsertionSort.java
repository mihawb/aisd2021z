package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            double temp = nums[i];
            int j = i - 1;
            while (nums[j] > temp) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = temp;
                   
        }
    }
}
