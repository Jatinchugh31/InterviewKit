package datastructure.src.string;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LongestSubstringWithDistinctCharacter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "abcdefabcbb";
        Integer ans = findSubString(s);
        System.out.println(ans);
    }

    private static Integer findSubString(String s) {
        int start = 0;
        int pointer = 0;
        int res = 1;
        Set<Character> set = new HashSet<>();

        while (pointer < s.length()) {
            if (set.contains(s.charAt(pointer))) {
                int tem = pointer - start;
                res = Math.max(res, tem);
                start = pointer;
                set.clear();
            }
            set.add(s.charAt(pointer));
            pointer++;
        }
        System.out.println(res);
        return res;
    }
}

