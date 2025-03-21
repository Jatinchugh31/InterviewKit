package src.dataStructure.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/*
 * To find the shortest substring that contains a specific set of characters in a given string, we need to ensure that the substring includes all characters from the target set (including their frequencies).
 * */
public class ShortestSubStringWithMatchedChars {

    public static void main(String[] args) {

        String str = "ADOBECODEBANC";
        String target = "ABC";
        String result = findTheSortestSubString(str, target);
        System.out.println(result);
    }

    private static String findTheSortestSubString(String str, String target) {
        Map<Character, Integer> targetCountMap = new HashMap<>();
        for (Character c : target.toCharArray()) {
            targetCountMap.put(c, targetCountMap.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int formedLength = 0;
        int requiredLength = targetCountMap.size();
        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        while (right < str.length()) {
            Character rightChar = str.charAt(right);
            windowMap.put(rightChar, windowMap.getOrDefault(rightChar, 0) + 1);
            if (windowMap.getOrDefault(rightChar, 0).intValue() == targetCountMap.getOrDefault(rightChar, 0)) {
                formedLength++;
            }

            if (left <= right && formedLength == requiredLength) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                char leftChar = str.charAt(left);
                windowMap.put(leftChar, windowMap.getOrDefault(leftChar, 0) - 1);
                if (windowMap.getOrDefault(leftChar, 0) < targetCountMap.getOrDefault(leftChar, 0)) {
                    formedLength--;
                }
                left++;
            }
            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : str.substring(minLeft, minLeft + minLength);

    }
}


/*
*
* Target Character Frequency: We create a map targetMap to store the frequency of each character in the target set.
Sliding Window:
We maintain a window defined by the left and right pointers.
The window expands by moving the right pointer and adds characters to windowMap.
Valid Window:
If the window contains all characters from the target set, we try to shrink the window by moving the left pointer.
We track the shortest valid window using minLength and minLeft.
Result:
Once the shortest valid window is found, we return it by extracting the substring from the original string.
If no valid window is found, we return an empty string.
Time Complexity:
O(n) where n is the length of the string. Each character is processed at most twice: once when the right pointer moves and once when the left pointer moves.
Space Complexity:
O(m) where m is the size of the target string. We use hash maps to store the frequencies of the target characters and the characters in the current window.
* */