package src.dataStructure.Array;

/**
 * Given two positive integer arrays arr and brr, find the number of pairs such that xy > yx (raised to power of) where x is an element from arr and y is an element from brr.
 * <p>
 * Input: arr[] = [2, 1, 6], brr[] = [1, 5]
 * Output: 3
 * Explanation: The pairs which follow xy > yx are: 2 of power1 > 1  of power2,  2 power5 > 52 and 61 > 16 .
 * <p>
 * Medium
 */
public class NumberOfPair {

    public static void main(String[] args) {
        int[] arr = {2, 1, 6}, brr = {1, 5};
        int ans = brutForce(arr, brr);
        System.out.println("brutForce " + ans);
    }

    private static int brutForce(int[] arr, int[] brr) {
        int i = 0;
        for (int x : arr) {
            for (int y : brr) {
                double temp1 = Math.pow(x, y);
                double temp2 = Math.pow(y, x);
                if (temp1 > temp2) {
                    i++;
                }

            }
        }
        return i;

    }

}
