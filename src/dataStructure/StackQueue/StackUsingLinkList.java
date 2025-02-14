package src.dataStructure.StackQueue;

class Node{
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
    }
}
public class StackUsingLinkList {
    static Node top = null;

    public static void main(String[] args) {
    push(1);
    push(2);
    push(3);
    push(4);
    pop();
    push(5);
    push(6);

    while (top != null){
        System.out.println(top.data);
        top = top.next;
    }
    }
    public  static void push(int data) {
        if(top == null ){
            top = new Node(data);
        }else {
            Node temp = new Node(data);
            temp.next = top;
            top = temp;
        }
    }
    public  static void pop() {
        if(top != null) {
            System.out.println(top.data);
            top = top.next;
        }
    }
}


