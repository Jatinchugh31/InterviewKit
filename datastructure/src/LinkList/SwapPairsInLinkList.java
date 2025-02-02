package datastructure.src.LinkList;

public class SwapPairsInLinkList {
  public static void main(String[] args) {
      Node head = new Node(1);
      head.next = new Node(2);
      head.next.next = new Node(3);
      head.next.next.next = new Node(4);
      head.next.next.next.next = new Node(5);
      head.next.next.next.next.next = new Node(6);
      head.next.next.next.next.next.next = new Node(7);
      head.next.next.next.next.next.next.next = new Node(8);
      head.next.next.next.next.next.next.next.next = new Node(9);


      head = swapNodes(head);
      printList(head);

  }
  private static void printList(Node head) {
      while (head != null) {
          System.out.print(head.data + " ");
          head = head.next;
      }
  }
  private static Node swapNodes(Node head) {
      Node temp = head;
      while (temp != null && temp.next != null) {
          int temp1 = temp.data;
          temp.data = temp.next.data;
          temp.next.data = temp1;
          temp = temp.next.next;
      }
      return head;
  }
}
