package src.dataStructure.string;

import java.util.*;

public class RomanToInteger {
    public static void main(String[] args) {





        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        Map<Integer, Character> romanMap = new HashMap<>();
        romanMap.put( 1,'I');
        romanMap.put( 5,'V');
        romanMap.put( 10,'X');
        romanMap.put( 50,'L');
        romanMap.put(100,'L');
        romanMap.put( 500,'D');
        romanMap.put( 1000,'M');

        Stack<Character> stack = new Stack<>();
        String roman = "LXXI";
        int total = 0;
        for (int i = 0; i < roman.length(); i++) {
                 if(i+1 < roman.length() && map.get(roman.charAt(i)) < map.get(roman.charAt(i+1))) {
                     total -= map.get(roman.charAt(i));
                 }else {
                     total += map.get(roman.charAt(i));
                 }
        }
        System.out.println(total);
        StringBuilder roman2 = new StringBuilder();
        while(total>0){

            if(total>= 1000 ){
                int div = total/1000;
                for(int i = 0; i < div; i++){
                    roman2.append(romanMap.get(1000));
                }
                total = total%1000;
            }
            if(total>= 500){
                int div = total/500;
                for(int i = 0; i < div; i++){
                    roman2.append(romanMap.get(500));
                }
                total = total%500;
            }
            if(total>= 100){
                int div = total/100;
                for(int i = 0; i < div; i++){
                    roman2.append(romanMap.get(100));
                }
                total = total%100;
            }
            if(total>= 50){
                int div = total/50;
                for(int i = 0; i < div; i++){
                    roman2.append(romanMap.get(50));
                }
                total = total%50;
            }
            if(total>= 10){
                int div = total/10;
                for(int i = 0; i < div; i++){
                    roman2.append(romanMap.get(10));
                }
                total = total%10;
            }
            if(total>= 5){
                int div = total/5;
                for(int i = 0; i < div; i++){
                    roman2.append(romanMap.get(5));
                }
                total = total%5;
            }
            if(total == 1){
                roman2.append(romanMap.get(1));
                total--;

            }

        }
        System.out.println(roman2.toString());


    }


}
