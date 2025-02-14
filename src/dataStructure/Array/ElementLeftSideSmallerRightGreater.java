package src.dataStructure.Array;

import java.util.Arrays;
import java.util.Scanner;

// find the first element in the array for which left side is smaller and right greater
public class ElementLeftSideSmallerRightGreater {
    // [4, 2, 5, 7]
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Length of array");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            arr[i] = a;
        }

        int result = findElement(arr);
        System.out.println(result);
    }

    private static int findElement(int[] arr) {

        int[] leftMax = new int[arr.length];
        leftMax[0] = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i - 1]);
        }
        System.out.println(Arrays.toString(leftMax));
        int rightMin = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (leftMax[i] < arr[i] && rightMin > arr[i]) {
                return arr[i];
            }
            rightMin = Math.min(rightMin, arr[i]);
        }
        return -1;
    }
}
