package src.dataStructure.tree.binary;


import java.util.*;

public class BinaryTreeLeftView {

    public static void main(String[] args) {
        Node node = createNode();
        List<Integer> leftView = leftView(node);
        System.out.println(leftView);
    }

    private static List<Integer> leftView(Node node) {
        List<Integer> leftView = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (queue.isEmpty() == false) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if(i ==0){
                    leftView.add(current.data);
                }
                if(current.left != null){
                    queue.add(current.left);
                }
                if(current.right != null){
                    queue.add(current.right);
                }
            }
        }
        return leftView;
    }

    static public Node createNode() {
        System.out.println("Enter root Data");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        if (data == -1) {
            return null;
        }
        Node root = new Node(data);
        System.out.println("Enter left child of " + data);
        root.left = createNode();
        System.out.println("Enter right child of " + data);
        root.right = createNode();
        return root;
    }
}
