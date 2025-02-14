package src.dataStructure.Array;

/**
 * Input: arr[] = [1,2,3,7,5], target = 12
 * Output: [2, 4]
 * Explanation: The sum of elements from 2nd to 4th position is 12.
 *
 *
 * */
public class IndexOfSubArraySum {

    public static void main(String[] args) {
        int [] arr ={1,2,3,7,5};
        int target =12;
        int i=0;
        int temp =0;
        int end =0;
        for(int k=0;k<arr.length;k++){
            temp += arr[k];

             while (temp > target  && i <k){
                 temp -= arr[i];
                 i++;
             }

            if(temp == target){
                end =k;
                break;
            }
        }
        System.out.println(i +" " +end);
    }

}
