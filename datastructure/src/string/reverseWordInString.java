package datastructure.src.string;

public class reverseWordInString {
    public static void main(String[] args) {
        String s = "Hello Bangkok:)";
        String arr[] = s.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(arr[arr.length - 1]);
        for (int k = arr.length - 2; k >= 0; k--) {
            sb.append(" ");
            sb.append(arr[k]);
        }
        System.out.println(sb.toString());


    }

}
