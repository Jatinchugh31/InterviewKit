package datastructure.src.tree.binary;

public class BinaryTree {
    public static void main(String[] args) {
        Node root = Node.createNode();
        postOrder(root);
        System.out.println();
        preOrder(root);
        System.out.println();
        inOder(root);
    }

    

    static public void preOrder(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static public void inOder(Node root) {
        if (root == null)
            return;
        inOder(root.left);
        System.out.print(root.data + " ");
        inOder(root.right);
    }

    static public void postOrder(Node root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
}
