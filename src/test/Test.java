package src.test;


import java.util.HashMap;
import java.util.Map;

public class Test {


    public static void main(String[] args) {
       int arr[] ={2,4,6,7,1,1,1,1,1,1};
       int target = 6;

       // way1   sliding window
        int low=0;
        int high = 0;
        int sum=0;
        int result=0;
        while(high < arr.length){
            sum = sum + arr[high];

            while(sum > target && low < high){
                sum = sum - arr[low];
                low++;
            }
            if(sum == target){
                result = Math.max(result,high-low+1);
                System.out.println("pair found"+result);
            }
            high++;
        }

        Map<Integer,Integer>  prefixSum =new HashMap<>();
          sum=0;
          result=0;
        for(int i=0;i<arr.length;i++){
              sum = sum + arr[i];
              if(sum == target){
                  result = Math.max(result,i+1);
              }

              if(prefixSum.containsKey(sum-target)){
                  result = Math.max(result,i - prefixSum.get(sum-target));

              }
              prefixSum.put(sum,i);
        }
        System.out.println(result);
    }




}