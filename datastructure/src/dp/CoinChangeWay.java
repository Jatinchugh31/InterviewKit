package datastructure.src.dp;

public class CoinChangeWay {

    public static void main(String[] args) {
        int amount =10;
        int[] coins  = {8,3,1,2};

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int coin : coins){
            for(int i = coin; i <= amount; i++){
                dp[ i] += dp[(i - coin)];
            }
        }
        System.out.println(dp[amount]);

    }
}
