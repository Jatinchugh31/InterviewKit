package src.dataStructure.Array;

import java.util.Arrays;

//sort a array in accending or desending without swapping and minimum moves
//DIFFERENCE BETWEEN TWO NUMBER IS 1
//
public class MinimumMove_NOTSolved {

    public static void main(String[] args) {

     //   int arr[] = {5, 7, 9, 4, 11}; //9  test case from example
  //      int arr[] ={1,4,3,2}; //4

                    //defference ADD
                   //30,31,32,33,34,35,36,37,38
        int arr[]= {30,34,30,17,25,14,7,22,6,2} ; //118    actual test case of compiler
                 //30,29,28,27,26,25,24,23,21
                 // difference add

        //some other logic to find hight of first tower

//       SORT -> // 4,5,7,9,11
//                         5, 7, 9, 4, 11
//               //decreasing 1 ARR     4 5 6 7 8
//               1+2+3+3++3
//    //expecteD 2 ARR     7 8 9 10 11
//                         5, 7, 9, 4, 11
//                         2+1 -+6+0
///*
        /**
         * sORT aRRAY
         * MAKE EXPECTED SORRTED AARAY
         * COUNT DIFFERENCE BETWEEN BOTH THE ARRAY
         * **/
        int[] clonedArray = arr.clone();
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int[] increasing = new int[arr.length];
        int[] decreasing = new int[arr.length];

        int min= arr[0];
        for(int i=0; i<arr.length; i++){
            decreasing[i] = min;
            min++;
        }
        int max= arr[arr.length-1];
        for(int i=arr.length-1; i >=0; i--){
            increasing[i] = max;
            max--;
        }
        System.out.println(Arrays.toString(increasing));
        System.out.println(Arrays.toString(decreasing));
        int order1 =0;
        int order2 =0;
        for(int i=0; i<arr.length; i++){
            order1 +=  Math.abs(clonedArray[i]-increasing[i]);
            order2 +=  Math.abs(clonedArray[i]-decreasing[i]);
        }
        System.out.println(order1);
        System.out.println(order2);
    }
}
