package src.dataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = new LinkedList<Integer>();

    public static void main(String[] args) {


    }

    //Function to push an element into stack using two queues.
    void push(int a) {
        //while first queue is not empty, we keep popping the front element
        //from first queue and storing that element in second queue.
        while (!q1.isEmpty()) {
            int k = q1.peek();

            //popping element from first queue.
            q1.remove();

            //pushing it into second queue.
            q2.add(k);
        }

        //q1 is empty now and we push the given number into first queue.
        q1.add(a);

        //while second queue is not empty, we keep popping the front element
        //from second queue and storing that element in first queue.
        while (!q2.isEmpty()) {
            int k = q2.peek();
            //popping element from second queue.
            q2.remove();

            //pushing it into first queue.
            q1.add(k);

        }
    }

    int pop() {
        if (!q1.isEmpty()) {
            //we store the front element from the queue in a
            //variable and pop that element.
            int r = q1.peek();
            q1.remove();

            //returning the popped element.
            return r;
        }
        //else we return -1.
        return -1;


    }


}
