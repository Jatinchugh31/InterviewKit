package src.dataStructure;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Input: n = 7, arr[] = {4, 3, 7, 8, 6, 2, 1}
 * Output: 1
 * Explanation:  After modification the array will look like 3 < 7 > 4 < 8 > 2 < 6 > 1, the checker in the driver code will produce 1.
 * */
public class ZigZag {
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter number of elements ");
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
        System.out.println("Enter element " + i + " ");
        arr[i] = sc.nextInt();
    }
     makeZigZag(arr);
    System.out.println("is Array zig zag " + Arrays.toString( arr));
}



    private static void makeZigZag(int[] arr) {
      boolean  greaterThenFlag = true;

      for (int i = 0; i < arr.length -1; i++) {
          if(greaterThenFlag){
              if(arr[i] > arr[i+1]){
                  swap(arr,i, i+1);
              }
              greaterThenFlag = false;
          }else {
              if(arr[i] < arr[i+1]){
                  swap(arr,i, i+1);
              }
              greaterThenFlag = true;
          }
      }
    }

    private static void swap(int[] arr, int i, int i1) {
       int temp = arr[i];
       arr[i] = arr[i1];
       arr[i1] = temp;
     }

}
