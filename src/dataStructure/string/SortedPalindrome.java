package src.dataStructure.string;
// this logic work  KMP
// we will reverse the string and combine them both .
// and then we will make tbale  of LSP  which is Longest subting for palindrom

public class SortedPalindrome {

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 =  new StringBuilder(s1).reverse().toString();
        String  combine = s1 + "#"+s2;
        int[] len = computeLen(combine);
        System.out.println(s2.substring(0, s1.length() - len[combine.length()-1])+s1);

    }

    static int[] computeLen(String combine) {
        int[] lps = new int[combine.length()];
        int len = 0;
        for (int i = 1; i < combine.length(); i++) {
            while (len >0 &&combine.charAt(i) != combine.charAt(i - 1)) {
                len = lps[len-1];
            }

            if (combine.charAt(i) == combine.charAt(i - 1)) {
                len++;
                lps[i] = len;
            }
        }
        return lps;


    }


}
