package src.dataStructure.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TargetIndeces {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 2, 3};
        int target = 2;
        System.out.println(targetIndices(nums, target));
    }

    private static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        int firstIndex = binarySearch(nums, target, true);
        int lastIndex = binarySearch(nums, target, false);
        if (firstIndex == -1) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            result.add(i);
        }
        return result;
    }

    private static int binarySearch(int[] nums, int target, boolean firstIndex) {

        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                index = mid;
                if (firstIndex) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return index;
    }
}
