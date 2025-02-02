package datastructure.src.Array;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElement {
    public static void main(String[] args) {
         int[] arr = {7, 10, 4, 3, 20, 15};
         int k = 3;
         bruitForce(arr,k);
         usingQueue(arr,k);
    }

    private static void usingQueue(int[] arr, int k) {
    Queue<Integer> q = new PriorityQueue<>();
    for (int i :arr){
        q.add(i);
    }
    for (int i = 1; i < k; i++){
        q.poll();
    }
    System.out.println(q.poll());

    }

    private static void bruitForce(int[] arr, int k) {

      for (int i = 0; i < 3; i++) {
          for (int j = i + 1; j < arr.length; j++) {
              if (arr[i] > arr[j]) {
                  int temp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = temp;
              }
          }
      }
      System.out.println(arr[k-1]);
    }
}
