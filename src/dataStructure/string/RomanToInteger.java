package src.dataStructure.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {

        List<String> romans = new ArrayList<>() {{
            add("XIV");
            add("CDXLIV");
            add("MCMXC");
            add("LXXXIII");
        }};
        Map<String, Integer> romanMap = new HashMap<String, Integer>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);

        romans.forEach(roman -> {
            int res = 0;
            for (int i = 0; i < roman.length(); i++) {

                int s1 = romanMap.getOrDefault(String.valueOf(roman.charAt(i)), -1);

                if (i + 1 < roman.length()) {
                    int s2 = romanMap.getOrDefault(String.valueOf(roman.charAt(i + 1)), -1);
                    if (s1 >= s2) {
                        res += s1;
                    } else {
                        res += s2 - s1;
                        i++;
                    }
                } else {
                    res += s1;
                }
            }
            System.out.println(res);
        });


    }


}
