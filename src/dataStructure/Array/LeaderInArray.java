package src.dataStructure.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an array arr of positive integers.
 * Your task is to find all the leaders in the array.
 * An element is considered a leader if it is greater than or equal to all elements to its right.
 * The rightmost element is always a leader.
 * */
public class LeaderInArray {
    public static void main(String[] args) {
        int[] arr ={16, 17, 4, 3, 5, 2};
        List<Integer> result = new ArrayList<>();
        int max = arr[arr.length - 1];
        for(int  i=arr.length-1;i>=0;i--){
            if(arr[i] >= max){
                max = arr[i];
                result.add(max);
            }
        }
        Collections.sort(result);
        System.out.println(result);
    }
}
