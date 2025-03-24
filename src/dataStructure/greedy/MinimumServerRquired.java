package src.dataStructure.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumServerRquired {

    public static void main(String[] args) {
        List<int[]> jobs = new ArrayList<>();
        jobs.add(new int[]{1, 4});
        jobs.add(new int[]{2, 6});
        jobs.add(new int[]{5, 8});
        jobs.add(new int[]{7, 9});

        jobs.sort(Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] a : jobs) {
            if(!pq.isEmpty() && pq.peek() <= a[0]){
                pq.poll();
            }
            pq.add(a[1]);
        }
        System.out.println(pq.size());
    }


}
