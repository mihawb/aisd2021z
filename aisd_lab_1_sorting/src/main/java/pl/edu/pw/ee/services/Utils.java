package pl.edu.pw.ee.services;

public class Utils {
    public static boolean assertSorted(double [] nums) {
        boolean result = true;
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            result = result && (nums[i] >= nums[i-1]);
            // once result is set to false,
            // it will stay this way till the end of the loop
        }

        return result;
    }
}
