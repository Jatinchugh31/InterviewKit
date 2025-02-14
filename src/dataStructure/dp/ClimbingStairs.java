package src.dataStructure.dp;

public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 3;
        int k = climbStairsDp(n);
        int t = climbStaris(n);
        System.out.println(t);
        System.out.println(k);
    }

    private static int climbStaris(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStaris(n - 1) + climbStaris(n - 2);
    }

    private static int climbStairsDp(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
