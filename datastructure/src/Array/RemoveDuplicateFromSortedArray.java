package datastructure.src.Array;

import java.util.Arrays;

public class RemoveDuplicateFromSortedArray {

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,2,3,3,3,4,4,5,6,7,7};
        int k=1;
        for(int i=1; i<arr.length; i++){
            if(arr[i] != arr[i-1]){
                arr[k] = arr[i];
                k++;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr,0,k)));
    }

}
