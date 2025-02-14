package src.dataStructure.string;

import java.util.Arrays;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] input = {"flower","flow","flight"};
      //  String[] input = {"hello", "hworld"};

        String commonPrefix = findCommonPrefix(input);
        System.out.println(commonPrefix);
       System.out.println(findLongestCommonPrefix(input));
    }

    private static String findCommonPrefix(String[] input) {
        int minLength = findMinLength(input);
        String commonPrefix = input[minLength];
        for (String str : input) {
            for (int i = 0; i < commonPrefix.length(); i++) {
                if (commonPrefix.charAt(i) != str.charAt(i)) {
                    commonPrefix = str.substring(0, i );
                }
            }
        }
        return commonPrefix;
    }


    public static String findLongestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Sort the array
        Arrays.sort(strs);

        // Take the first and last strings in the sorted array
        String first = strs[0];
        String last = strs[strs.length - 1];
        StringBuilder commonPrefix = new StringBuilder();
        commonPrefix.isEmpty();
        // Compare characters of the first and last strings
        for (int i = 0; i < first.length(); i++) {
            if (i < last.length() && first.charAt(i) == last.charAt(i)) {
                commonPrefix.append(first.charAt(i));
            } else {
                break;
            }
        }

        return commonPrefix.toString();
    }

    private static int findMinLength(String[] input) {
        int minLength = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[minLength].length() > input[i].length()) {
                minLength = i;
            }
        }
        return minLength;
    }
}
