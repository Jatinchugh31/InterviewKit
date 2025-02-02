package datastructure.src.dp;

public class Robbery {

    public static void main(String[] args) {
        int[] house  = {2,7,9,3,1};
        if(house.length == 1){
            System.out.println(house[0]);
            return;
        }

        int[] dp = new int[house.length];
        dp[0] = house[0];
        dp[1] = Math.max(house[1], house[0]);
        for(int i = 2; i < house.length; i++){
            dp[i] = Math.max(dp[i-1], house[i] + dp[i-2]);
        }
        System.out.println(dp[house.length-1]);
    }
}
