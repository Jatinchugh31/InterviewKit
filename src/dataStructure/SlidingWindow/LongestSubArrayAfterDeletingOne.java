package src.dataStructure.SlidingWindow;


/**
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 * Return 0 if there is no such subarray.
 * */
public class LongestSubArrayAfterDeletingOne {

    public static void main(String[] args) {

        int[] arr =  {1,0,1,1,1,0,0,1,1,1,1,1,0,1};
          longestSubArray(arr);
    }

    //
    // indexOf 1 = 0
    // first  index 1 =  1
  //  length  (1-0 ) =1

    //start  2
    private static void longestSubArray(int[] arr) {

       int left=0 , zeroCount = 0, maxLength = 0;

       for(int right =0 ; right < arr.length ; right++) {
           if(arr[right] == 0)
               zeroCount++;

           while(zeroCount > 1) {
               if(arr[left] == 0){
                   zeroCount--;
               }
               left++;
           }
           maxLength = Math.max(maxLength, right - left + 1);

       }
       System.out.println(maxLength);
    }
}
