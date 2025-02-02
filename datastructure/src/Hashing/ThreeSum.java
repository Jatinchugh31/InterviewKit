package datastructure.src.Hashing;

import java.util.HashSet;
import java.util.Set;

public class ThreeSum {
public static void main(String[] args) {
   int [] arr = {12, 3, 4, 1, 6, 9};
   int  sum = 24;
   for(int i = 0; i < arr.length; i++){

       int target = sum - arr[i];
       Set<Integer> set = new HashSet<>();
       for(int j = i+1; j < arr.length; j++){
          if(set.contains(target - arr[j])){
              System.out.println(arr[i] + "\t" + arr[j] + "\t" + (target -arr[j]) + "\t" );
              break;
          }
          set.add(arr[j]);
       }
   }

}
}
