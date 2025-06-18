package src.companies.epam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindSecondNonRepeatingCharacter {

    public static void main(String[] args) {
        String str = "java is my software";
        //output will be v.
        str.chars().mapToObj(c  -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1)
                .skip(1)
                .findFirst().map(Map.Entry::getKey)
                .ifPresentOrElse(System.out::println,()-> System.out.println("not found"));
    }
}
