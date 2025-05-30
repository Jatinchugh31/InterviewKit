package src.dataStructure.Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllSubSequence {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        List<List<Integer>> list = new ArrayList<>();
        backTracking(arr, 0, new ArrayList<>(), list);
        System.out.println(list);
    }


    public static void backTracking(int[] arr, int start, List<Integer> current, List<List<Integer>> result) {

        result.add(new ArrayList<>(current));
        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            backTracking(arr, i + 1, current, result);
            current.removeLast();
        }

    }
}
