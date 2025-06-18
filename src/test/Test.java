package src.test;


import java.util.HashMap;
import java.util.Map;


public class Test {


    public static void main(String[] args) {

        int arr[] = {1,1,2,3};

        int x=1;
        int low =0;
        int high=arr.length-1;
        int res=-1;
        while(low<=high){
            int mid=low+(high-low)/2;

            if(arr[mid]==x){
                res=mid;
                high--;
            }else if(arr[mid]<x){
                low=mid+1;
            }else {
                high=mid-1;
            }
        }
        System.out.println(res);

    }


}