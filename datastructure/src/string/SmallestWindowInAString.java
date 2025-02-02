package datastructure.src.string;

import java.util.Arrays;

public class SmallestWindowInAString {

    public static void main(String[] args) {
        String s = "timetopractice", p = "toc";

        smallestWindowBruteForce(s, p);
        smallestWindowHashApproch(s, p);
    }

    private static void smallestWindowHashApproch(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();

        //if length of string p is greater than string s then we return -1.
        if (len1 < len2) {
            System.out.println("-1");
            return;
        }

        //using hash tables to store the count of characters in strings.
        int[] hash_pat = new int[256];
        int[] hash_str = new int[256];

        //storing the count of characters in string p in hash table.
        for (int i = 0; i < len2; i++) {
            hash_pat[p.charAt(i)]++;
        }

        int start = 0;
        int start_index = -1;
        int min_len = Integer.MAX_VALUE;
        int count = 0;
        for (int j = 0; j < len1; j++) {
            //storing the count of characters in string s in hash table.
            hash_str[s.charAt(j)]++;

            //if count of current character in string s is equal to or less
            //than in string p, we increment the counter.
            if (hash_pat[s.charAt(j)] != 0
                    && hash_str[s.charAt(j)] <= hash_pat[s.charAt(j)]) {
                count++;
            }

            //if all the characters are matched
            if (count == len2) {
                while (hash_str[s.charAt(start)] > hash_pat[s.charAt(start)] ||
                        hash_pat[s.charAt(start)] == 0) {
                    //we try to minimize the window.
                    if (hash_str[s.charAt(start)] > hash_pat[s.charAt(start)]) {
                        hash_str[s.charAt(start)]--;
                    }
                    start++;
                }
                //updating window size.
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    //if new minimum sub-string is found, we store value
                    //of its starting index for new found window.
                    min_len = len_window;
                    start_index = start;
                }
            }

        }
        //returning the smallest window or -1 if no such window is present.
        if (start_index == -1) {
            System.out.println("-1");
        } else {
            System.out.println(s.substring(start_index, start_index + min_len));
        }
    }


    static boolean containAllChars(String str, String pattern) {

        int[] chars = new int[256];
        for (Character c : pattern.toCharArray()) {
            chars[c]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (chars[str.charAt(i)] > 0)
                chars[str.charAt(i)]--;
        }
        for (int c : chars) {
            if (c > 0) {
                return false;
            }
        }
        return true;
    }

    static boolean smallestWindowBruteForce(String str, String pattern) {
        int m = str.length();
        int n = pattern.length();
        String smallestSubstring = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < m; j++) {
                String substr = str.substring(i, j);
                if (containAllChars(substr, pattern)) {
                    if (substr.length() < minLen) {
                        minLen = substr.length();
                        smallestSubstring = substr;
                    }
                }
            }
        }
        System.out.println(smallestSubstring);
        return smallestSubstring.length() < minLen;
    }
}
