package datastructure.src.string;

/*
*
* Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
* of the characters without disturbing the relative positions of the remaining characters.
*  (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
*
**/
public class IsSubsequence {

    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        int sp=0;
        int fp=0;

        while(sp < s.length() && fp < t.length()){
            if(s.charAt(sp) == t.charAt(fp)){
                sp++;
            }
            fp++;
        }

        System.out.println(sp == s.length());
    }
}
