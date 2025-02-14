package src.dataStructure.StackQueue;

import java.util.Stack;

public class ValidParenthesis {

    public static void main(String[] args) {
        String s = "{[])}";
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);

            }else {
                if (stack.empty()) {
                    System.out.println("not valid");
                    break;
                }else {
                    Character top = stack.pop();
                    if(c == ')' && top == '('){

                    }else if (c == '}' && top == '{'){

                    }else if (c == ']' && top == '['){

                    }else {
                        System.out.println("not valid");
                        break;
                    }
                }
            }
        }

        if(stack.empty()){
            System.out.println("valid");
        }else {
            System.out.println("not valid");
        }
    }
}
