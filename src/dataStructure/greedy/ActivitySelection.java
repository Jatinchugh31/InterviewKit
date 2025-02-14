package src.dataStructure.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ActivitySelection {

    public static void main(String[] args) {
        int start[] = { 1, 3, 0, 5, 8, 5 };
        int end[] = { 2, 4, 6, 7, 9, 9 };
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.end));

        for (int i = 0; i < start.length; i++) {
            Pair a = new Pair(start[i], end[i]);
            pq.add(a);
        }

        List<Pair> result = new ArrayList<>();
        Pair p = pq.poll();
        int i = p.start;
        int j = p.end;
        result.add(p);
        while (!pq.isEmpty()) {
            Pair a = pq.poll();
            if (a.start >= j) {
                i = a.start;
                j = a.end;
                result.add(new Pair(i, j));
            }
        }
        result.forEach(System.out::println);
    }

    static class Pair {
        public int start;
        public int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return  "{" +
                    "start" +start +
                    "end" + end +"}";
        }
    }
}
