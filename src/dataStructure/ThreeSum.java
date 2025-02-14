package src.dataStructure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ThreeSum {
    /**
     * Input: array = {12, 3, 4, 1, 6, 9}, sum = 24;
     * Output: 12, 3, 9
     * Explanation: There is a triplet (12, 3 and 9) present
     * in the array whose sum is 24.
     * <p>
     * Input: array = {1, 2, 3, 4, 5}, sum = 9
     * Output: 5, 3, 1
     * Explanation: There is a triplet (5, 3 and 1) present
     */
    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 1, 6, 9};
        int target = 24;
        findTriplet1(arr, target);
        findTriplet2(arr, target);

    }

    private static void findTriplet2(int[] arr, int target) {
        exitLoop:

        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set = new HashSet<>();
            int currentSum = target - arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int required_value = currentSum - arr[j];
                if (set.contains(required_value)) {
                    System.out.println(arr[i] + "+ " + arr[j] + " + " + required_value + " = " + target);
                    break exitLoop;
                }
                set.add(arr[j]);


            }
        }
    }

    private static void findTriplet1(int[] arr, int target) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int start = i + 1, end = arr.length - 1;
            while (start < end) {
                int sum = arr[i] + arr[start] + arr[end];
                if (sum == target) {
                    System.out.println(arr[i] + "+ " + arr[start] + " + " + arr[end] + " = " + sum);
                    break;
                } else if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
    }
}
