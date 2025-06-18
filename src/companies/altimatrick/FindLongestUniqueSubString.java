package src.companies.altimatrick;

import java.util.HashSet;

public class FindLongestUniqueSubString {
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0; // left pointer of sliding window

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Shrink window until duplicate is removed
            while (set.contains(currentChar)) {
                set.remove(s.charAt(left));
                left++;
            }

            // Expand window
            set.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Longest substring without repeating characters: " +
                lengthOfLongestSubstring(s)); // Output: 3
    }

}
