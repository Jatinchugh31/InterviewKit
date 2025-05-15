package src.dataStructure.string;

public class CompressString {
    public static void main(String[] args) {
        String a = "abcd";
        System.out.println(compressedString(a));
    }

    public static String compressedString(String word) {
        int counter =1;
        char lastChar = word.charAt(0);
        StringBuilder res = new  StringBuilder();
        for(int i=1 ; i < word.length() ; i++){
            char current = word.charAt(i);
            if(current != lastChar ||  counter >= 9){
                res.append(counter);
                res.append(lastChar);
                lastChar = current;
                counter =1;
            } else {
                counter++;
            }
            if(i == word.length()-1){
                res.append(counter);
                res.append(lastChar);
            }
        }
        return res.toString();
    }
}
