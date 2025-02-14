package src.dataStructure.string;

public class LongestPalindrome {

    public static void main(String[] args) {
        String str = "aaaabbaa";
        int n = str.length()-1;

        for(int i =n ; i >= 0 ; i--){
            int mid = (i/2)+1;
            str = str.substring(0,i);
            System.out.println(mid +"-"+ i);
            String sub1 = str.substring(0,mid);
            String sub2 = str.substring(mid,i);
            sub2 = reverseString(sub2);
            if(sub1.equals(reverseString(sub2))){
                System.out.println("Palindrome");
                System.out.println(sub1);
                System.out.println(sub2);
            }else {
                System.out.println("Not Palindrome");
                System.out.println(sub1);
                System.out.println(sub2);
            }

        }

    }

    private static String reverseString(String sub1) {
    StringBuilder s = new StringBuilder(sub1);
      return s.reverse().toString();
    }

}
