package datastructure.src;

import java.util.*;

/**
 * Example 1:
 *
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 * Example 2:
 *
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * Example 3:
 *
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
 *
 */
public class MakeSumDivisibleByP {
public static void main(String[] args) {
    int[] nums = {3,1,4,2};
    int p=6;

    int totalSum = 0;
    for(int i=0; i<nums.length; i++){
        totalSum+=nums[i];
    }
    if(totalSum%p==0){
        System.out.println(0);
        return;
    }
    int rim= totalSum%p;
    System.out.println(rim);
    for(int i=0; i<nums.length; i++){
        if(nums[i]==rim){
            System.out.println(nums[i]);
            break;
        }
    }
   int[] arr = getSubArraySum(nums,rim );
   System.out.println(arr.length);
}

    private static int[] getSubArraySum(int[] nums, int rim) {
    int last = 0;
    int start =0;
    long currSum = 0;
    boolean flag = false;
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i=0; i<nums.length; i++){
        currSum += nums[i];
        if(currSum >= rim){
            last = i;

            while (rim < currSum && start < last){
                currSum -= nums[start];
                ++start;
            }
            if(currSum == rim){
                flag = true;
                list.add(start+1);
                list.add(last+1);
                break;
            }
        }
    }
    if(flag){
      return   Arrays.copyOfRange(nums,start,last+1);
    }else {
        return nums;
    }
}

}
