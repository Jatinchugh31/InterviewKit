package src.dataStructure.StackQueue;

import java.util.Stack;

public class QueueUsingTwoStack {
    Stack<Integer> stack1 = new Stack<>(); //main
    Stack<Integer> stack2 = new Stack<>(); //helping stack

    public static void main(String[] args) {

        QueueUsingTwoStack queue = new QueueUsingTwoStack();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.print();
        queue.pop();
        queue.print();
        queue.pop();
        queue.print();
        queue.peek();
        queue.push(5);
    }

    public void push(int x) {
        stack1.push(x);

    }

    public int pop() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return -1;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int data = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        System.out.println("\npop\n " + data);
        return data;
    }

    public int peek() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return -1;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int data = stack2.peek();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        System.out.println("\npeek\n " + data);
        return data;
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    public void print() {
        System.out.println("\nprint");

        for (int i = 0; i < stack1.size(); i++) {
            System.out.print(stack1.get(i) + " ");
        }
    }
}
