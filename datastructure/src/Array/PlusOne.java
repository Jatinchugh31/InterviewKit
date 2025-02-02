package datastructure.src.Array;

import java.util.Arrays;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class PlusOne {

    public static void main(String[] args) {
        int[] arr = {9, 9, 9};
        Stack<Integer> stack = new Stack<>();
        int n = arr.length - 1;
        int num = arr[n];
        int carry = 0;
        for (int i = n; i >= 0; i--) {
            num = arr[i];

            if (i == n) {
                num = num + 1;
            }
            num = num + carry;
            if (num > 9) {
                carry = num - 9;
                num = 0;
            } else {
                carry = 0;
            }
            stack.push(num);
        }
        if (carry > 0) {
            stack.push(carry);
        }
        int[] ans = new int[stack.size()];
        n = stack.size();
        for (int i = 0; i < n; i++) {
            ans[i] = stack.pop();
        }
        System.out.println(Arrays.toString(ans));

    }

}
