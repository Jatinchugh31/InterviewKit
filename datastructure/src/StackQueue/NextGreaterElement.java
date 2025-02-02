package datastructure.src.StackQueue;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args) {

        int arr[] = {6, 8, 0, 1, 3};
        bruitForceSolution(arr);
        stackSolution(arr);
    }

    private static void stackSolution(int[] arr) {
       //        int arr[] = {6, 8, 0, 1, 3};
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek()<= arr[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                res[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        System.out.println(Arrays.toString(res));

    }

    private static void bruitForceSolution(int[] arr) {
        int res[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            boolean found = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (temp < arr[j]) {
                    res[i] = arr[j];
                    found = false;
                    break;
                }
            }

            if (found) {
                res[i] = -1;
            }
        }

        System.out.println(Arrays.toString(res));
    }

}
