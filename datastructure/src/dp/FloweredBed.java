package datastructure.src.dp;

/*
*
* You are given an array of 0s and 1s where 0 represents and
* empty block and 1 represents a block with a flower bed.
* You are given an integer k and you need to determine
* if you can put k more flower beds in the
*
*  empty blocks given the rule that any 2 flower beds cannot be adjacent to each other.
*
* */
public class FloweredBed {

    public static void main(String[] args) {
        int[] space = {1,0,0,0,1,0,1,0,0,0,1};
        int k= 4;
        int freeSpace = caclulteFreeSpace(space);

        System.out.println(freeSpace >= k);
    }

    private static int caclulteFreeSpace(int[] space) {
       int count  =0;
        for(int i=0;i<space.length;i++){

            //check for free space
            if(space[i] ==0){
                boolean leftEmpty = (i == 0) || (space[i - 1] == 0);
                boolean rightEmpty = (i == space.length - 1) || (space[i + 1] == 0);

                if (leftEmpty && rightEmpty) {
                    space[i] = 1; // Place a flower
                    count++; // Increment the count

                }
            }
        }
return count;
    }

}
