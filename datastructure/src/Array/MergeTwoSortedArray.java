package datastructure.src.Array;

import java.util.Arrays;

public class MergeTwoSortedArray {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {0 ,2, 6, 8, 9};
        int i = 0;
        withExtraSpace(arr1, arr2);
        withoutExtraSpace(arr1, arr2);
    }

    private static void withoutExtraSpace(int[] arr1, int[] arr2) {

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    private static void withExtraSpace(int[] arr1, int[] arr2) {

        int[] newArray = new int[arr1.length + arr2.length];
        int i = 0;
        int arr1Length = 0;
        int arr2Length = 0;

        while (arr1Length < arr1.length && arr2Length < arr2.length) {
            if (arr1[arr1Length] < arr2[arr2Length]) {
                newArray[i] = arr1[arr1Length];
                arr1Length++;
            } else {
                newArray[i] = arr2[arr2Length];
                arr2Length++;
            }
            i++;
        }
        if (arr1Length < arr1.length) {
            while (arr1Length < arr1.length) {
                newArray[i] = arr1[arr1Length];
                arr1Length++;
                i++;
            }
        }
        if (arr2Length < arr2.length) {
            while (arr2Length < arr2.length) {
                newArray[i] = arr2[arr2Length];
                arr2Length++;
                i++;
            }
        }
        System.out.println(Arrays.toString(newArray));

    }
}
