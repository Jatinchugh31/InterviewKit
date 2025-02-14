package src.dataStructure.LinkList;

import java.util.HashSet;
import java.util.Set;

public class LoopInLinkList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head.next;
        detactLoop1(head);
        detactLoop(head);
    }

    private static void detactLoop1(Node head) {
    Set<Node> set = new HashSet<>();
       while (head != null) {
           if (set.contains(head)) {
               System.out.println("loop found");
               return;
           }
           set.add(head);
           head = head.next;
       }
    }

   public static void detactLoop(Node head){
        Node slow=head;
        Node fast=head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                System.out.println("loop found");
                return;
            }
        }
   }

}
