package src.dataStructure.tree.binary;

/*
* To count "good" nodes in a binary tree,
* we define a good node as a node that is greater than
* or equal to all nodes in the path from the root to that node.
* */
public class GoodNodes {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);

        GoodNodes solution = new GoodNodes();
        System.out.println("Number of good nodes: " + solution.countGoodNodes(root)); // Output: 4

    }

    private int countGoodNodes(TreeNode root) {
       return  dfs(root,root.val);
    }

    private int dfs(TreeNode root, int maxVal) {
        if(root==null)
            return 0;

        int good  = (root.val >= maxVal) ? 1 : 0;
        maxVal = Math.max(maxVal,root.val);
        good += dfs(root.left,maxVal);
        good += dfs(root.right,maxVal);
        return good;
    }

}
