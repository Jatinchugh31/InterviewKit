package datastructure.src.Array;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IntersectionOfTwoArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2,2,3}, nums2 = {1, 2};
        Map<Integer, Long> map = new HashMap<>();
        if (nums1.length < nums2.length) {
            map = Arrays.stream(nums2).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            int[] solution = findIntersaction(map, nums1);
            System.out.println(Arrays.toString(solution));
        } else {
            map = Arrays.stream(nums1).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            int[] solution = findIntersaction(map, nums2);
            System.out.println(Arrays.toString(solution));
        }

    }

    private static int[] findIntersaction(Map<Integer, Long> map, int[] nums1) {
        List<Integer> list = new ArrayList<>();
        for (Integer i : nums1) {
            if (map.containsKey(i)) {
                list.add(i);
                Long k = map.get(i) - 1;
                if (k == 0) {
                    map.remove(i);
                } else {
                    map.put(i, k);
                }
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
