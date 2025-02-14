package src.dataStructure.DivideAndConcure;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//O(logN)
public class FindUniqueElementSortedArray {
 public static void main(String[] args) {
       int[] arr = {1,1,2,2,3,3,4,5,5,6,6,7,7,8,8,7,9,9,9};
       bruitForceSolutin(arr);
       divideAndConquire(arr);
 }

    private static void divideAndConquire(int[] arr) {
       int low = 0 , high = arr.length - 1;

         while (low < high) {
             int mid = low + (high - low) / 2;
             if (mid % 2 == 1) {
                 mid--;
             }

             if (arr[mid] == arr[mid + 1]) {
                 // Unique element must be in the right half
                 low = mid + 2;
             } else {
                 // Unique element must be in the left half
                 high = mid;
             }
         }
         System.out.println(arr[low]);

 }

    private static void bruitForceSolutin(int[] arr) {

        Map<Integer,Long> groupBy = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(i->i,Collectors.counting()));
        AtomicInteger ans =new AtomicInteger(0);
        groupBy.forEach((k,v)->{
            if(v==1){
                ans.set(k);
            }
        });
        System.out.println(ans);
 }
}
