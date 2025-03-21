package src.theory.collections;

import java.util.*;
import java.util.stream.Collectors;

public class SortMap {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("a", 1);
        map.put("b", 3);
        map.put("c", 3);
        map.put("d", 2);
        map.put("e", 5);
        map.put("f", 5);

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey() ,entry.getValue());
        }
        sortedMap.forEach((k,v)->{System.out.print(k+" "+v);});
System.out.println();

        //by key
        Map<String, Integer> sortedMap2 = new TreeMap<>(sortedMap);
        sortedMap2.forEach((k,v)->{System.out.print(k+" "+v);});

System.out.println();
        list.sort(Comparator.comparing(Map.Entry<String,Integer>::getKey).thenComparing(Map.Entry<String,Integer>::getValue));


    LinkedHashMap<String,Integer> sorted =     map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String,Integer>::getValue).thenComparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue , newValue) -> oldValue , LinkedHashMap::new));
    System.out.print(sorted);

    }

}
