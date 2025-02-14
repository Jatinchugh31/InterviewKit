package src.dataStructure.LinkList;

public class ReverseALinkList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        print(head);
        System.out.println();
       head = reverseList(head);
        print(head);


    }

    private static Node reverseList(Node head) {
       Node current = head;
       Node prev = null;
       while (current != null) {
           Node next = current.next;
           current.next = prev;
           prev = current;
           current = next;
       }
       return prev;
    }

    public static void print(Node head){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
   }

}
