package src.dataStructure.tree.binary;

import java.util.Scanner;

/***
 *           1
 *       2           3
 *    4      5     6    7
 *
 * */
public class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
    }


    static public Node createNode() {
        System.out.println("Enter Data");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        if (data == -1) {
            return null;
        }
        Node root = new Node(data);
        System.out.println("Enter left child  " + data);
        root.left = createNode();
        System.out.println("Enter right child " + data);
        root.right = createNode();
        return root;
    }
}
