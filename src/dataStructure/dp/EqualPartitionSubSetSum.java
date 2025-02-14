package src.dataStructure.dp;

import java.util.Arrays;

// divide an array into two parts that both sums are equals
public class EqualPartitionSubSetSum {
    public static void main(String[] args) {
        int[] arr = {1, 5, 7, 4, 5};
        boolean result = isPossible(arr);
        System.out.println(result);
    }

    private static boolean isPossible(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : arr) {

            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[target];
    }

}
