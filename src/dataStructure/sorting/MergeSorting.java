package src.dataStructure.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSorting {

    public static  void main(String args[]){
        int[] arr = {10,8,7,1,3,2,5,9,4,11};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr) {
       if(arr.length<=1){
           return;
       }
       int mid = arr.length/2;
       int[] left = new int[mid];
       int[] right = new int[arr.length-mid];

       System.arraycopy(arr,0,left,0,mid);
       System.arraycopy(arr,mid,right,0,arr.length-mid);
       mergeSort(left);
       mergeSort(right);
       merge(left,right,arr);
    }

    private static void merge(int[] left, int[] right, int[] arr) {
       int leftLength = left.length;
       int rightLength = right.length;
       int leftIndex = 0;
       int rightIndex = 0;
       int index = 0;
       while (leftIndex < leftLength && rightIndex < rightLength) {
           if (left[leftIndex] <= right[rightIndex]) {
               arr[index] = left[leftIndex];
               leftIndex++;
           }else  {
               arr[index] = right[rightIndex];
               rightIndex++;
           }
           index++;
       }
       while (leftIndex < leftLength) {
           arr[index] = left[leftIndex];
           leftIndex++;
           index++;
       }

       while (rightIndex < rightLength) {
           arr[index] = right[rightIndex];
           rightIndex++;
           index++;
       }
    }


}
