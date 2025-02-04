package datastructure.src.tree.binary;
/*
* The lowest common ancestor (LCA) of two nodes
𝑝
p and
𝑞
q in a binary tree is defined as the deepest node that has both
𝑝
p and
𝑞
q as its descendants (where we allow a node to be a descendant of itself).
* */
public class LowestCommonAncestor {

  public  static  void  main(String[] args) {
      Node root = new Node(1);
      root.left = new Node(2);
      root.right = new Node(3);
      root.left.left = new Node(4);
      root.left.right = new Node(5);
      root.right.left = new Node(6);
      root.right.right = new Node(7);
      root.left.left.left = new Node(8);
      root.left.left.right = new Node(9);
      root.left.right.left = new Node(10);
      root.left.right.right = new Node(11);
      root.left.right.left.left = new Node(12);
      root.left.right.left.right = new Node(13);
      Node lca  =lowestCommonAncestor(root,root.left,root.right);
  }

    private static Node lowestCommonAncestor(Node root, Node p, Node q) {

       if (root == null || p == null || q == null) {
           return root;
       }

        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

       if(left != null && right != null) {
           return root;
       }
        return left != null ? left : right; // Return non-null subtree result

  }
}
