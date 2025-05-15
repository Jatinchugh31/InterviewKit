package src.dataStructure.Array;
//https://leetcode.com/problems/longest-nice-subarray/description/
/*
We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0

*Input: nums = [1,3,8,48,10]
Output: 3
Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.
* */

import java.util.ArrayList;
import java.util.List;

public class NiceSubArray {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 48, 10};

        int res = longestNiceSubarray(arr);
        System.out.println(res);
    }


    public static int longestNiceSubarray(int[] nums) {
        int usedBits = 0; // Tracks bits used in current window
        int windowStart = 0; // Start position of current window
        int maxLength = 0; // Length of longest nice subarray found

        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            // If current number shares bits with window, shrink window from left
            // until there's no bit conflict
            while ((usedBits & nums[windowEnd]) != 0) {
                usedBits ^= nums[windowStart]; // Remove leftmost element's bits
                windowStart++;
            }

            // Add current number to the window
            usedBits |= nums[windowEnd];

            // Update max length if current window is longer
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

}
