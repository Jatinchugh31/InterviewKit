package src.dataStructure.dp;

import java.util.Arrays;

/*
* Given two strings s1 and s2,
* return the length of their longest common subsequence (LCS).
* If there is no common subsequence, return 0.

A subsequence is a sequence that can be derived from the given string by
* deleting some or no elements without changing the order of the remaining elements.
*  For example, "ABE" is a subsequence of "ABCDE".
* */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String str = "ABCDGH";
        String str2 = "AEDFHR";

        int[][] dp = new int[str.length()+1][str2.length()+1];
        for(int temp[] : dp){
            Arrays.fill(temp,0);
        }
        for(int i = 1; i <=str.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                if(str.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
      System.out.println(dp[str.length()][str2.length()]);
    }

}
