package datastructure.src.DivideAndConcure;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int target = 8;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                System.out.println(mid);
                break;
            }else if (arr[mid] > target) {
                high = mid - 1;

            }else {
                low = mid + 1;
            }
        }
    }

}
