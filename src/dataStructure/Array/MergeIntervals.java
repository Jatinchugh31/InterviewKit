package src.dataStructure.Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
public static void main(String[] args) {
//    Example: [[1,3],[2,6],[8,10],[15,18]] should become [[1,6],[8,10],[15,18]].
    int[][] arr = {{1,3},{2,6},{8,10},{15,18}};
    List<Pair> pairs = new ArrayList<>();
    for(int[] arr1 : arr){
       pairs.add(new Pair(arr1[0],arr1[1]));
    }
    pairs.sort(Comparator.comparingInt(p -> p.start));
    List<Pair> mergedIntervals = new ArrayList<>();
    Pair current = pairs.get(0);
    for(int  i=1;i<pairs.size();i++){
        Pair next = pairs.get(i);
        if(current.end >= next.start){
           current.end = Math.max(current.end,next.end);
        }else{

            mergedIntervals.add(current);
            current = next;
        }
    }
    mergedIntervals.add(current);

    System.out.println(mergedIntervals);

}

private static  class Pair{
   int start;
   int end;
   Pair(int start,int end){
       this.start=start;
       this.end=end;
   }

   @Override
     public String toString(){
       return "["+start + " " + end +"]";
   }
}
}
