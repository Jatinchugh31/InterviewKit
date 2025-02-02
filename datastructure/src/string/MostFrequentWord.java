package datastructure.src.string;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
*
* Input:
N = 3
arr[] = {geeks,for,geeks}
Output: geeks
Explanation: "geeks" comes 2 times.
* */
public class MostFrequentWord {
public static void main(String[] args) {
    String[] arr = {"hello","world"};

    int res =0;
    String word="";
    Map<String, Integer> map = new LinkedHashMap<>();
    for(String str : arr){
        if(map.containsKey(str)){
            map.put(str, map.get(str)+1);
        }else {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        if(map.get(str) >= res){
            res = map.get(str);
            word = str;
        }
    }

    System.out.println(res);
    System.out.println(word);


}

}
