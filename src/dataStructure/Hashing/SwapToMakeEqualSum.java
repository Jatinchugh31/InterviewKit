package src.dataStructure.Hashing;

import java.util.Arrays;

public class SwapToMakeEqualSum {

    public static void main(String[] args) {
        int n = 4, m = 4;
        long[] a = {5, 7, 4, 6}, b = {1, 2, 3, 8};
//                   22    14
//                   18 ,18
//                    -2       4
       long target = getTarget(a,n,b,m);
        if (target == Long.MAX_VALUE)
        {
            System.out.println("No solution found");
            return;
        }

        int i=0, j=0;

        while (i<n  && j <n){

            long diff = a[i]-b[j];
            if(diff == target){
                System.out.println("Solution found");
                return;
            }else if(diff < target){
                i++;
            }else{
                j++;
            }

        }

    }

   static long getTarget(long a[], int n, long b[], int m) {
        long sum1  = Arrays.stream(a).sum();
        long sum2  = Arrays.stream(b).sum();

        // If the difference between the sums is not even, return max value.
        if ((sum1 - sum2) % 2 != 0) return Long.MAX_VALUE;
        return ((sum1 - sum2) / 2);
    }

}
