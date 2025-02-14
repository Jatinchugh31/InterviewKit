package src.dataStructure.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKDistinct {

    public static void main(String[] args) {
        String s = "abbcabcbbaabazthsn";
        int k=3;

        int start =0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();

        for(int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                  map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                  if(map.get(s.charAt(start)) == 0) {
                      map.remove(s.charAt(start));
                  }
                  start++;
            }
            maxLength = Math.max(maxLength, right - start);
        }
        System.out.println(maxLength);
    }

}
