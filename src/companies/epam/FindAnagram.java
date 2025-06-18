package src.companies.epam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAnagram {
    // CODE EXAMPLE VALID FOR COMPILING
        public static void main(String[] args) {
            System.out.println("Hello, World!");
            String[] input =  {"act","pots","tops","cat","stop","hat"};
            Map<String, List<String>> res =new HashMap<>();
            for(String str : input){
                int[] count  = new int[26];
                for(char c : str.toCharArray()){
                    count[c-'a']++;
                }

                StringBuilder sb = new StringBuilder();
                for(int num : count){
                    sb.append(num).append("#");
                }
                String key = sb.toString();
                res.computeIfAbsent(key , k -> new ArrayList<>()).add(str);
            }

            res.forEach((key,value) -> {
                System.out.println("for key" + key);
                if(value != null){
                    System.out.println(value);
                }
                System.out.println();
            });
        }

}
