package datastructure.src.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class NMeetingInRoom {
    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        Pair[] pair = new Pair[start.length];
        for (int i = 0; i < start.length; i++) {
            pair[i] = new Pair(start[i], end[i]);
        }
        Arrays.sort(pair, new Comparator<Pair>() {
            @Override
            public int compare(Pair s1, Pair s2) {
                return s1.end - s2.end;
            }
        });

        int last = -1;
        int res = 0;
        for (int i = 0; i < pair.length; i++) {
            if (pair[i].start > last) {
                res++;
                last = pair[i].end;
            }
        }
        System.out.println(res);
    }

    static class Pair {
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}

