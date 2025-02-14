package src.dataStructure.Array;

import java.util.Arrays;


public class RoateArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

      withExtraSpace(nums,k);
      withoutExtraSpace(nums,k);
    }

    private static void withoutExtraSpace(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k >= n

        // Reverse the entire array
        System.out.println(k);
        reverse(nums, 0, n - 1);

        // Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Reverse the remaining n-k elements
        reverse(nums, k, n - 1);
        System.out.println("without extra space"+Arrays.toString(nums));


    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }


    }

    private static void withExtraSpace(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k >= n
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }

        // Copy the temp array back to nums
        System.out.println("with extra space" +Arrays.toString(temp));
    }

}
