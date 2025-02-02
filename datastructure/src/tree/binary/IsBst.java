package datastructure.src.tree.binary;

public class IsBst{
    public static void main(String[] args) {
        Node tree  = Node.createNode();
       boolean isBst = isBstTree(tree);
       System.out.println(isBst);
    }

    private static boolean isBstTree(Node tree) {
    return  checkIsBst(tree,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private static boolean checkIsBst(Node tree, int minValue, int maxValue) {
     if (tree == null) {
         return true;
     }
     if(tree.data <= minValue || tree.data >= maxValue) {
         return false;
     }
     return  checkIsBst(tree.left,minValue,tree.data)
             &&
             checkIsBst(tree.right,tree.data,maxValue);
    }


}
