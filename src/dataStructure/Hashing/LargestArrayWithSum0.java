package src.dataStructure.Hashing;

import java.util.HashMap;
import java.util.Map;

public class LargestArrayWithSum0 {

    public static void main(String[] args) {
        // int[] arr= {15,-2,2,-8,1,7,10,23};
        //  int[] arr=  {2,10,4};
        int[] arr = {1, 0, -4, 3, 1, 0};
        int maxLen = maxLen(arr);
        System.out.println(maxLen);

    }

    private static int maxLen(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (arr[i] == 0 || maxLen == 0) {
                maxLen = 1;
            }
            if (sum == 0) {
                maxLen += i;
            }

            Integer previousIndex = map.get(sum);
            if (previousIndex != null) {
                maxLen = Math.max(maxLen, i - previousIndex);
            } else {
                map.put(sum, i);
            }
        }

        return maxLen;
    }
}
