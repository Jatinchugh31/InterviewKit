package src.dataStructure.Array;

import java.util.*;

/*
*
* nput: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.
*
* */
public class finalScore {

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 1, 3, 2};
        int score = 0;
        while (!allMarked(nums)) {
            score += findSmallestAndMark(nums);
        }
        System.out.println("score" + score);
        int[] nums1 = {2, 3, 5, 1, 3, 2};

        score = 0;
       score=  optimised(nums1);
        System.out.println("score" + score);
    }

    private static int optimised(int[] nums) {
        int score = 0;

        // Initialize a priority queue (min-heap) to store indices by their values
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> nums[i]));

        // Add all indices to the priority queue
        for (int i = 0; i < nums.length; i++) {
            pq.add(i);
        }

        // Set to track marked indices
        Set<Integer> marked = new HashSet<>();

        while (!pq.isEmpty()) {
            // Get the smallest unmarked index
            int idx = pq.poll();

            // If this index has already been marked, skip it
            if (marked.contains(idx)) {
                continue;
            }

            // Add to score
            score += nums[idx];

            // Mark the element and its neighbors
            marked.add(idx);
            if (idx > 0) marked.add(idx - 1);
            if (idx < nums.length - 1) marked.add(idx + 1);
        }
      return score;
    }

    private static int findSmallestAndMark(int[] nums) {

        int temp = Integer.MAX_VALUE;
        int k = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1 && nums[i] < temp) {
                temp = nums[i];
                k = i;
            }
        }
        if (temp != Integer.MAX_VALUE) {
            nums[k] = -1;
            if (nums.length > 1) {
                if (k == 0) {
                    nums[k + 1] = -1;
                } else if (k == nums.length - 1) {
                    nums[k - 1] = -1;
                } else {
                    nums[k + 1] = -1;
                    nums[k - 1] = -1;
                }
            }

                return temp;

        }
        return 0;
    }

    private static boolean allMarked(int[] marked) {
        return Arrays.stream(marked).allMatch(num -> num == -1);
    }

}
