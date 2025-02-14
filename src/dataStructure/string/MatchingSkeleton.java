package src.dataStructure.string;

import java.util.ArrayList;
import java.util.List;

public class MatchingSkeleton {

    //valid skelotons are if  same skelton contains missing char;
    public static void main(String[] args) {
        String s = "hello";
        String[] skeleton = {"h_ll_", "he_lo", "hell_", "hello"};
        List<String> list = new ArrayList<String>();
        for (String s1 : skeleton) {
            char ch[] = s1.toCharArray();
            if (s.equalsIgnoreCase(s1)) {
                list.add(s1);
            } else {
                for (int i = 0; i < ch.length; i++) {
                    if (ch[i] == '_') {
                        char missingChar = s.charAt(i);
                        if (s.contains(String.valueOf(missingChar))) {
                            list.add(s1);
                        }else {
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(list);
    }
}
