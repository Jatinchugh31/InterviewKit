package src.dataStructure.dp;

import java.util.Arrays;

public class StrictlyLongestSubArray {
    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 7, 9, 1,10};
        way1(arr);
    }

    //
    private static void way1(int[] arr) {
        System.out.println("this a tabulation soltuin we will store maxmimum subarray at each index comlexity is O(n^2) with o(n) space") ;
        int[] dp = new int[arr.length+1];
        int res=1;
        Arrays.fill(dp,1);
        for(int i=1; i <= arr.length-1; i++) {
            for(int j=0; j <=i;j++){
                if(arr[j]< arr[i]){
                   dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        for(int dpi : dp){
            res = Math.max(res,dpi);
        }
        System.out.println(res);

    }
}
