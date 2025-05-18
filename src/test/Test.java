package src.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
         String str1 = "A man, a plan, a canal: Panama";
         str1 = str1.replaceAll("[^a-zA-Z]", "").toLowerCase().trim();
        int start =0;
        int end =str1.length()-1;
        while (start < end) {
            if(str1.charAt(start) != str1.charAt(end)) {
                System.out.println(false);
                return;
            }
            start++;
            end--;
        }

        System.out.println(true);
    }


}
