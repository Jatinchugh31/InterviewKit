package src.dataStructure.string;

import java.util.Arrays;

public class RemoveDuplicateFromString {
public static void main(String[] args) {

    //remove dublicate and give first oocurence
    // space O(1) and time complaxity O(n)
    String  input = "zvvo";

    int[] characters = new int[256];
    Arrays.fill(characters,0);
    StringBuilder builder = new StringBuilder();
    for(int i=0 ; i < input.length() ; i++){
        if(characters[input.charAt(i)] == 0){
            builder.append(input.charAt(i));
        }
        characters[input.charAt(i)]++;
    }
    System.out.println(builder.toString());
}
}
