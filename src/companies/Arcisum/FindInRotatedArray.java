package src.companies.Arcisum;

// now we have two sotrted array in a single array and we need to elemenat one
// we can check if mid <= target then definatly it is in left array
public class FindInRotatedArray {

    public static void main(String[] args) {
        int[] input = {89,90,100,1,2,5,8};
        int target = 90 ;

        int low = 0;
        int high = input.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (input[mid] == target) {
                System.out.println(mid);
                break;
            } else if(input[low] <= input[mid]) {
                if(input[low]<=target && target<=input[mid]) {
                    high = high - 1;
                }else {
                    low = low + 1;
                }
            }else {
                if(input[mid]<=target && target<=input[high]) {
                    low = low + 1;
                }else {
                    high = high - 1;
                }
            }
        }
    }


}
