package datastructure.src.LinkList;

public class DeleteNode {
   public static void main(String[] args) {
       Node head = new Node(1);
       head.next = new Node(2);
       head.next.next = new Node(3);
       head.next.next.next = new Node(4);
       head.next.next.next.next = new Node(5);
        int i=3;
        Node temp = head;
       while (i >0){
           temp = temp.next;
           i--;
       }
       deleteNode(temp);
       printNode(head);
   }

    private static void printNode(Node head) {
     Node temp = head;
     while (temp != null) {
         System.out.print(temp.data + " ");
         temp = temp.next;
     }
   }

    private static void deleteNode(Node node) {
       System.out.println(node.data);
       if (node == null || node.next == null) {
        return;
       }

       Node nextNode = node.next;
       node.data = nextNode.data;
       node.next = nextNode.next;

   }
}
