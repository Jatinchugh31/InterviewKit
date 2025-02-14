package src.dataStructure.Array;

import java.util.Arrays;

/**
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5,
 * with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * */
public class removeDuplicatesSecond {

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3,3,3,4,4};
        int k=2;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] != arr[k-2] ) {
                arr[k++] = arr[i];
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, k)));

    }

}
