package datastructure.src.LinkList;


import static datastructure.src.LinkList.ReverseALinkList.print;

public class DeleteNthNode {
  public static void main(String[] args) {
      Node head = new Node(1);
      head.next = new Node(2);
      head.next.next = new Node(3);
      head.next.next.next = new Node(4);
       head.next.next.next.next = new Node(5);
       head.next.next.next.next.next = new Node(6);
      head =  deleteNthNode(head,3);
       print(head);

  }

    private static Node deleteNthNode(Node head, int i) {

      Node dummy = new Node(0);
      dummy.next = head;

      Node slow = dummy;
      Node fast = dummy;
      for (int j = 0; j <= i; j++) {
          fast = fast.next;
      }
      while (fast != null) {
          fast = fast.next;
          slow = slow.next;
      }
      slow.next = slow.next.next;
      return dummy.next;
     }
}
