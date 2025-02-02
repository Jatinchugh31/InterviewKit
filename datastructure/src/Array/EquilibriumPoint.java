package datastructure.src.Array;

public class EquilibriumPoint {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 2};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
            if (sum == leftSum) {
                System.out.println(i + 1);
                return;
            }
            leftSum += arr[i];
        }
        System.out.println(-1);
    }
}
