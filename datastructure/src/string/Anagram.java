package datastructure.src.string;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Input:a = geeksforgeeks, b = forgeeksgeeks
 * Output: YES
 * Explanation: Both the string have same characters with
 * same frequency. So, both are anagrams.
 **/

public class Anagram {
    public static void main(String[] args) {
      System.out.println(isAnagram("anagram", "aanagaram"));

    }

    public static boolean isAnagram(String a, String b) {
        Map<Character, Long> charactersCount = a.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (char c : b.toCharArray()) {
            if (charactersCount.containsKey(c)) {
                if (charactersCount.get(c) > 1) {
                    charactersCount.put(c, charactersCount.get(c) - 1);
                } else {
                    charactersCount.remove(c);
                }
            } else {
                return false;
            }
        }
        return charactersCount.isEmpty();
    }
}
