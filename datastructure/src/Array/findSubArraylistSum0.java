package datastructure.src.Array;

import java.util.HashMap;

public class findSubArraylistSum0 {

    public static void main(String[] args) {
        int[] n = {4, 2, -3, 1, 6,-6};

        findSubarraySumBF(n);
        findSubarraySumMap(n);
    }

    private static void findSubarraySumMap(int[] n) {

        long sum = 0, counter = 0;
        //using Hashmap to store the prefix sum which has appeared already.
        HashMap<Long, Long> mp = new HashMap<>();

        for (int i = 0; i < n.length; i++) {
            sum += n[i];

            if (sum == 0) {
                counter++;
            }

            if (mp.containsKey(sum)) {
                counter += mp.get(sum);
            }
            if (mp.containsKey(sum)) {
                long temp = mp.get(sum);
                temp += counter;
                mp.put(sum, temp);
            } else {
                mp.put(sum, 1L);
            }
        }
        System.out.println(counter);
    }

    public static void findSubarraySumBF(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
