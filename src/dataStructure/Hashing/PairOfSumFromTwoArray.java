package src.dataStructure.Hashing;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Note: All pairs should be returned in increasing order of u. For eg. for two pairs (u1,v1) and (u2,v2), if u1 < u2 then (u1,v1) should be returned first else second.
 * */
public class PairOfSumFromTwoArray {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 5, 7};
        int[] arr2 = {5, 6, 3, 4, 8};
        int sum =9;
        Set<Integer> set1 = Arrays.stream(arr1).boxed().sorted(Integer::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
        Set<Integer> set2 = Arrays.stream(arr2).boxed().sorted(Integer::compareTo
        ).collect(Collectors.toCollection(LinkedHashSet::new));

        for(Integer i : set1){
            if(set2.contains(sum -i)){
                System.out.println(i +" " + (sum -i));
            }
        }

    }
}
