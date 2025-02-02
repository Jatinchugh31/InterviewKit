package datastructure.src.tree.binary;

public class HighOfBinaryTree {



    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(8);
        root.right.left.right = new Node(9);

        System.out.println("tree high is -> " + calculateTreeHeight(root));
    }

    private static int calculateTreeHeight(Node root) {
      if (root == null) {
          return 0;
      }
      int leftHeight = calculateTreeHeight(root.left);
      int rightHeight = calculateTreeHeight(root.right);
      return Math.max(leftHeight, rightHeight) + 1;
    }
}
