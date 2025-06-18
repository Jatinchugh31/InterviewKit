package src.companies.fico;

//hosting a kidParty  , give a big with  N number of packet
// distributed a packet to 1 kid , each packat has different candies
//distribute entire 1 packet to 1 kid
//job is distributed this packet rationaly as possibliy
//kids will got max candies - kid with least number of candies this should be the
//possible number from the given set
//2  3 5 17 ,
//if 3 kids   distributed 3 ,5 .17
// with least is 3 and kid with max 17
//so difference is 14  ,
//so picking 17 is wrong choice
//so out put will ne 2 3 5


import java.util.Arrays;

// solution we can implemet greedy+ sliding window in this
//first sort the candis
// then make a window of k ,   calulte diff between both
// if the result is min that one is our result
public class DistributeCandies {

    public static void main (String[] args) {
        int[] candies = {2,17,5,3};
        int kids = 3;

        Arrays.sort(candies);
        int minDif=Integer.MAX_VALUE;
        int[] result =  new int[3];
        for(int i=0;i<candies.length-kids;i++){
            int dif = Math.abs(candies[i]-candies[i+kids-1]);
            if(dif<minDif){
                minDif=dif;

                result =  Arrays.copyOfRange(candies, i, i + kids);

            }
        }
        System.out.println(Arrays.toString(result));
    }

}


//other question related to collections
// conccurenthashMap vs syncronisedHashMap
// conccurenthashMap in detail internal working with cross questions
//multiuple example like 3 thread are doing get put remove how they will perform