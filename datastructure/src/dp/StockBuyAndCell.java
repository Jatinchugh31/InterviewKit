package datastructure.src.dp;

import java.util.ArrayList;

class Interval {
    int buy;
    int sell;
}

public class StockBuyAndCell {
    public static void main(String[] args) {
        int arr[] = {100, 180, 260, 310, 40, 535, 695};

        ArrayList<ArrayList<Integer>> ans = findDays(arr);
        int c=0;
        for(int i=0; i<ans.size(); i++)
            c += arr[ans.get(i).get(1)]-arr[ans.get(i).get(0)];

        System.out.println("Maximum profite "+c);
        System.out.println( "res"+way2(arr));
    }

    private static Integer way2(int[] arr) {
        int profit = 0;

        // Loop through the arr array starting from the second element (index 1)
        for (int i = 1; i < arr.length; i++) {
            // Check if the price today (arr[i]) is greater than the price yesterday
            // (arr[i-1]). This means there's an opportunity for profit, as buying
            // yesterday and selling today would yield profit.
            if (arr[i - 1] < arr[i]) {
                // Add the difference (profit) to the total profit.
                profit += arr[i] - arr[i - 1];
            }
        }

        // Return the accumulated profit after processing all days.
        return profit;
    }

    private static ArrayList<ArrayList<Integer>> findDays(int[] arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (arr.length <= 1) return result;
        ArrayList<Interval> sol = new ArrayList<Interval>();
        int i = 0, cnt = 0;

        while (i < arr.length - 1) {
            while (i < arr.length - 1 && (arr[i + 1] <= arr[i])) {
                i++;
            }
            if (i == arr.length - 1) {
                System.out.println("No solution found");
                break;
            }
            Interval interval = new Interval();
            interval.buy = i++;
            System.out.println("buy at day " + interval.buy);

            while (i < arr.length  && (arr[i] >= arr[i - 1])) {
                i++;
            }
            interval.sell = i-1;
            System.out.println("sell at day " + interval.sell);

            sol.add(interval);
        }
        for (int j = 0; j < sol.size(); j++) {
            result.add(new ArrayList<Integer>());
            result.get(j).add(0, sol.get(j).buy);
            result.get(j).add(1, sol.get(j).sell);
        }
        return result;

    }
}
