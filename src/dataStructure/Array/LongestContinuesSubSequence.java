package src.dataStructure.Array;

import java.util.*;

public class LongestContinuesSubSequence {
/// You are given an array of ‘N’ integers.
/// You need to find the length of the longest sequence which contains the consecutive elements.
    public static void main(String[] args) {
      int[] input  = {100, 200, 1, 3, 2, 4,9,10,11,12,13,14};
      //output is 1,2,3,4
        optimised(input);
        better(input);

  }

    private static void better(int[] input) {
       Arrays.sort(input);
       int start=0;
       int end =0;
       int res=0;
       while(end<input.length-1){
           if(input[end+1] - input[end] != 1){
               start=end;
           }
           res = Math.max(res,end-start+1);
           end++;
       }
       System.out.println(res);
    }

    private static void optimised(int[] input) {
        Set<Integer> nums = new HashSet<>();
        Arrays.stream(input).boxed().forEach(nums::add);
        int longest = 0;
        for(int i : input){
            if(!nums.contains(i-1)){
                int res =1;
                int cnt =i;
                while(nums.contains(cnt+1)){
                    res ++;
                    cnt++;
                }
                longest = Math.max(res,longest);
            }
        }
        System.out.println(longest);
    }

}
