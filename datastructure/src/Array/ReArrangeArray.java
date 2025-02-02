package datastructure.src.Array;

import java.util.*;

/*
*
* Input:
n = 6
arr[] = {1,2,3,4,5,6}
Output: 6 1 5 2 4 3
Explanation: Max element = 6, min = 1,
second max = 5, second min = 2, and
so on... Modified array is : 6 1 5 2 4 3
* */
public class ReArrangeArray {

    public static void main(String[] args) {
       int[] arr = {1,2,3,4,5,6}  ;

        printUsingQueue(arr);
        twoPointerApproch(arr);
    }


    private static void twoPointerApproch(int[] arr) {
     int n = arr.length;
     int maxIdx = n-1;
     int minIdx= 0;
     int maxElement =arr[n-1]+1;

     for(int i =0 ; i <  n; i++){
         if(i %2 ==0 ){
             arr[i] += (arr[maxIdx] % maxElement) * maxElement;
             maxIdx--;
         } else {
             // Odd index: Place minimum element
             arr[i] += (arr[minIdx] % maxElement) * maxElement;
             minIdx++;
         }
     }
        for (int i = 0; i < n; i++) {
            arr[i] /= maxElement;
        }
        System.out.println(Arrays.toString(arr));

    }

    private static void printUsingQueue(int[] arr) {
        Queue<Integer> q1 = new PriorityQueue<>(Integer::compareTo);
        Queue<Integer> q2 = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            q1.offer(arr[i]);

            q2.offer(arr[i]);
        }

        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if(flag){
                arr[i] = q2.poll();
                flag = false;
            }else {
                arr[i] = q1.poll();
                flag = true;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
