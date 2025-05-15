package src.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1,2,1,4,5,6,0,9};

        int start = 0;
        int end =0;
        int res = 0;
        for(int i=1;i<arr.length;i++){
            if(arr[i] - arr[i-1]==1){
                end=i;
            }else {
                int temp  = end  - start+1;
                res = Math.max(res,temp);
                start = i;
            }
        }

        System.out.println(res);
    }
}
