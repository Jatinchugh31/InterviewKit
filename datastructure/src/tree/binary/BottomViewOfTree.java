package datastructure.src.tree.binary;

import java.util.*;

/**
 * To compute the bottom view of a binary tree, you can follow these steps:
 * <p>
 * Assign Horizontal Distances: Start from the root, assigning it an HD of 0. For each node:
 * <p>
 * Assign the left child an HD of HD - 1
 * Assign the right child an HD of HD + 1
 * Store Nodes by HD: Use a queue for level-order traversal, keeping track of each node’s HD. Use a map (or dictionary) to store the node's value at each HD. Update the map each time a node is encountered at the same HD; the most recent node will overwrite any previous nodes, representing the “bottommost” node at that HD.
 * <p>
 * Extract Bottom View: After traversal, the map will contain the bottommost nodes at each HD. The bottom view is the set of values ordered by their HDs.
 **/
public class BottomViewOfTree {

    static class Pair {
        TreeNode node;
        int hd;

        public Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static List<Integer> bottomViewOfTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode cur = p.node;
            int hd = p.hd;
            map.put(hd, cur.val);
            if (cur.left != null) {
                q.add(new Pair(cur.left, hd - 1));
            }
            if (cur.right != null) {
                q.add(new Pair(cur.right, hd + 1));
            }
        }
        return new ArrayList<>(map.values());

    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = right = null;
    }
}
