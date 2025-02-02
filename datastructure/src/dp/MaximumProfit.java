package datastructure.src.dp;
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solutions/3667440/beats-100-c-java-python-beginner-friendly/?envType=study-plan-v2&envId=leetcode-75
/**
 *
 * Given an array prices where prices[i] is the price of a given stock on the
 * 𝑖
 * 𝑡
 * ℎ
 * i
 * th
 *   day and an integer fee representing a transaction fee:
 *
 * You may complete as many transactions as you like.
 * However, you must sell the stock before buying again.
 * Return the maximum profit you can achieve.
 *
 * To solve LeetCode 714: Best Time to Buy and Sell Stock with Transaction Fee, we aim to maximize our profit while considering a transaction fee deducted for each sell operation.
 * */
public class MaximumProfit {

    public static void main(String[] args) {
        int[] prices = {1,3,2,8,4,9};
        int fee=2;
        int maxProfit = maxProfit(prices,fee);
        System.out.println(maxProfit);
    }

    private static int maxProfit(int[] prices, int fee) {
       int n = prices.length;
       int hold = -prices[0];
       int cash =0;
       for (int i = 1; i < n; i++) {
           int prevHold = hold;
           hold = Math.max(hold, cash - prices[i] );
           cash = Math.max(cash, prevHold + prices[i] - fee );
       }
       return cash;
    }

}
