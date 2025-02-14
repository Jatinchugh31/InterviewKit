package src.dataStructure.Hashing;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * */
public class HappyNumber {

 public static void main(String[] args) {
     int n =19;

     boolean isHappy = isNumberHappy(n);
     System.out.println(isHappy);
 }

    private static boolean isNumberHappy(int n) {
        Set<Integer> visted = new HashSet<>();
        while (!visted.contains(n)){
            visted.add(n);

            n = getNextNumber(n);
            if(n==1){
                return true;

            }

        }
        return false;
 }

    private static int getNextNumber(int n) {
       int sum = 0;
       while(n>0){
           int digit = n%10;
           sum = sum + digit*digit;
           n = n/10;
       }
       return sum;
     }
}
