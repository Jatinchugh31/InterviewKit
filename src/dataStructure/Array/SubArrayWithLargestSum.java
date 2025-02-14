package src.dataStructure.Array;

public class SubArrayWithLargestSum {
public static void main(String[] args) {

    int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
    int maxSum  = Integer.MIN_VALUE;
    int sum = nums[0];
    for(int right = 1; right < nums.length; right++) {
        sum = Math.max(sum +nums[right],nums[right]);
        maxSum = Math.max(maxSum,sum);
    }
    System.out.println(maxSum);

}
}
