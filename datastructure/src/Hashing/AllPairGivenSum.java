package datastructure.src.Hashing;

import java.util.HashMap;

public class AllPairGivenSum {

    public  static void main(String[] args) {
        int sum  =9;
        int[] arr = {1, 2, 4, 5, 7};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(map.containsKey(sum - arr[i])) {
                System.out.println(map.get(sum - arr[i]) + " " + arr[i]);
            }else {
                map.put(arr[i], i);
            }
        }
    }



}
