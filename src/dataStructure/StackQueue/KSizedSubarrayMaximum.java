package src.dataStructure.StackQueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class KSizedSubarrayMaximum {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] arr = findKMaximumSubArray(nums, k);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] findKMaximumSubArray(int[] nums, int k) {
        if (nums == null || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input.");
        }

        int[] result =  new int[nums.length -k +1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if(!deque.isEmpty() && deque.peekFirst() == i-k) {
                deque.pollFirst();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
