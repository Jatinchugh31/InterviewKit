package src.dataStructure.StackQueue;


import java.util.*;

/**
 * Given an input stream A of n characters consisting only of lower case alphabets.
 * While reading characters from the stream, you have to tell which character has
 * appeared only once in the stream upto that point.
 * If there are many characters that have appeared only once,
 * you have to tell which one of them was the first one to appear.
 * If there is no such character then append '#' to the answer.
 * */
public class FirstNonRepeatingCharacterInAStream {
public static void main(String[] args) {
    String A = "abbcdd";

    int[] characters = new int[26];
    Arrays.fill(characters,0);
    Deque<Character> queue = new LinkedList<>();
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < A.length(); i++) {
         int   index = A.charAt(i) - 'a';
         characters[index]++;
         queue.addLast(A.charAt(i));
         while (!queue.isEmpty() && characters[queue.getFirst()-'a'] >1) {
             queue.removeFirst();
         }

         if(queue.isEmpty()){
             res.append("#");
         }else {
             res.append(queue.peek());
         }

    }
    System.out.println(res);
}
}
