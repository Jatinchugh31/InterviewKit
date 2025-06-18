package src.companies.sixt;
/*
* we have an array if o and 1 , we need to group all 1 together in a way it take less move or shit
* it can be circular array like adjecent  element can  consier as one group
*
* input  = [0,1,0,1,1,0]
* */
public class FindMinimumMove {
    public static void main(String[] args) {
        int[] input  = {0,1,0,1,1,0};
        int n = input.length;
        //first we will count the number of one
        int countOfOne =0;
        for(int num : input){
            if(num == 1){
                countOfOne++;
            }
        }

        if(countOfOne ==0 || countOfOne == n){
            System.out.println("No minimum move found");
            return;
        }

        int[] doubleOfArray = new int[2*n];
        for(int i = 0 ; i < 2 * n ; i++){
            doubleOfArray[i] = input[i%n];
        }

        int oneInCurrentWindow =0;
        int maxOne =0;
        for(int i=0;i < countOfOne;i++){
            if(doubleOfArray[i] == 1){
                oneInCurrentWindow++;
            }
        }
        maxOne = oneInCurrentWindow;

        for(int i=countOfOne ; i < 2 * n ; i++){
            if(doubleOfArray[i] == 1){
                oneInCurrentWindow++;
            }
            if(doubleOfArray[i - countOfOne] == 1){
                oneInCurrentWindow--;
            }
            maxOne = Math.max(maxOne, oneInCurrentWindow);
        }
        System.out.println(countOfOne -maxOne);
    }

}

//we will make a window ,
//first we will check in first window
//then we will make a count  in current window how many ones
// are present and removing from last