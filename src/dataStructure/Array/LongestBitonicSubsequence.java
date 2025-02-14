package src.dataStructure.Array;

// a bitonic  subsequence is  first strictly increasing the decresing

/*
* Input: arr[]= [12, 11, 40, 5, 3, 1]
Output: 5
Explanation: The Longest Bitonic Subsequence is {12, 40, 5, 3, 1} which is of length 5.


Input: arr[] = [80, 60, 30]
Output: 0
Explanation: There is no possible Bitonic Subsequence.
*
* */

public class LongestBitonicSubsequence {
   public static void main(String[] args) {
       int[] arr = {12, 11, 40, 5, 3, 1};

      bruitForce(arr);
   }

    private static void bruitForce(int[] arr) {
       int maxLength = 0;
       for (int i = 1; i < arr.length-1; i++) {
            int leftLen = left(i, i-1,arr);
            int rightLen = righ(i,i+1,arr);

            if(leftLen == 0 || rightLen == 0 ) {
                leftLen =0;
                rightLen =0;
            }
            maxLength = Math.max(maxLength, leftLen+rightLen+1);
       }
       System.out.println(maxLength < 3 ? 0 : maxLength);
   }

    private static int righ(int prev, int idx, int[] arr) {
      if(idx >= arr.length){
          return 0;
      }
      int include  =0;
      if(arr[idx] < arr[prev]){
          include = 1 + righ(idx, idx+1, arr);
      }
      return Math.max(include, righ(prev, idx+1, arr));
   }

    private static int left(int prev, int idx, int[] arr) {
        if (idx < 0) {
            return 0;
        }

        int include = 0;
        if (arr[idx] < arr[prev]) {
            include = 1 + left(idx, idx - 1, arr);
        }
        return Math.max(include, left(prev, idx - 1, arr));

    }
}
