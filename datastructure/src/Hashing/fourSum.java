package datastructure.src.Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class fourSum {
    public static void main(String[] args) {

        int arr[] = {10, 20, 30, 40, 1, 2};
        int n = arr.length;
        int X = 91;

        // Function call
        findFourElements(arr, n, X);
    }

    static class pair {
        int first, second;

        public pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static void findFourElements(int[] arr, int n, int x) {
        HashMap<Integer, pair> mp
                = new HashMap<Integer, pair>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                mp.put(arr[i] + arr[j], new pair(j, j));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];
                if (mp.containsKey(x - sum)) {
                    pair p = mp.get(x - sum);
                    if (p.first != i && p.first != j
                            && p.second != i && p.second != j) {
                        System.out.print(
                                arr[i] + ", " + arr[j] + ", "
                                        + arr[p.first] + ", "
                                        + arr[p.second]);
                        return;
                    }
                }
            }
        }
    }
}
