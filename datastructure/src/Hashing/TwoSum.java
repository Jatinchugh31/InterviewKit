package datastructure.src.Hashing;

import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 17;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(target - arr[i])) {
                System.out.println(arr[i] + " " + (target - arr[i]));
                break;
            }
            set.add(arr[i]);
        }


        Arrays.sort(arr);
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            if (arr[start] + arr[end] == target) {
                System.out.println(arr[start] + " " + arr[end]);
                break;
            } else if (arr[start] + arr[end] < target) {
                start++;
            } else {
                end--;
            }

        }

        //index
        int[] arr2= {2,7,11,15};

        Map<Integer,Integer > map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            if (map.containsKey(arr2[i] - target)) {
                System.out.println(arr2[i] + " " + (arr2[i] - target));
            }
            map.put(arr2[i], i);
        }
    }
}
