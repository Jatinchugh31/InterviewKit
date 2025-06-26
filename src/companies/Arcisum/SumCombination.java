package src.companies.Arcisum;

import java.util.ArrayList;
import java.util.List;

public class SumCombination {

    public static void main(String[] args) {
        int[] arr = {2,3,5,7};
        int target = 7;
        // possible solution =  [2,2,3],[2,5],[7]

        List<List<Integer>> result = calculateSubSetTarget(arr,target);
        System.out.println(result);
    }

    private static List<List<Integer>> calculateSubSetTarget(int[] arr, int target) {

        List<List<Integer>> result = new ArrayList<>();
        backTracking(arr,0,target,result,new ArrayList<>());
        return result;
    }

    private static  void backTracking(int[] arr,int start, int target, List<List<Integer>> result, List<Integer> temp) {
       if(target == 0) {
           result.add(new ArrayList<>(temp));
       }

       for(int i = start; i < arr.length; i++) {
           if( arr[i]> target) {
               break;
           }
           temp.add(arr[i]);
           backTracking(arr,i,target-arr[i],result,temp);
           temp.remove(temp.size()-1);
       }

    }

    private static void backTracking() {
    }

}
