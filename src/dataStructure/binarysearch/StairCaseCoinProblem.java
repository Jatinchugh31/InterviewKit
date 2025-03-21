package src.dataStructure.binarysearch;

//Wallmart

//Input: n = 5
//Output: 2
//Explanation:
//The staircase formation:
//⬤
//⬤ ⬤
//⬤ ⬤ ⬤  (Incomplete row)
//Since the third row is incomplete, the maximum complete rows are **2**.
public class StairCaseCoinProblem {

    public  static void main(String[] args){
      System.out.println(maxRows(8));
        System.out.println(bruitForce(8));

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
    private static int bruitForce(int coins) {
       int coinUsed = 1;
       int coinLeft = coins -coinUsed;
       while (coinLeft > (coinUsed+1)) {
              coinUsed++;
              coinLeft =  coinLeft -coinUsed ;
       }
       return coinUsed;
    }



    static long  maxRows(int n){
        long left=1 , right=n;
        while(left<right){
            long mid =  left+(right-left)/2;
            long coinUsed = (mid* (mid+1))/2;  // this math problem will tell  how much coind used at thsi point
            if(coinUsed == n){
                return  coinUsed;
            } else  if(coinUsed < n){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return right;
    }

}
