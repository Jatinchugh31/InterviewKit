package src.dataStructure.tree.binary;

public class MaximumGold {

    public static void main(String[] args) {

        int[][] grid ={{0,6,0},{5,8,7},{0,9,0}};
      int maxGold = findMaxGold(grid);
      System.out.println(maxGold);
    }

    private static int findMaxGold(int[][] grid) {
      int maxGold =0;
      int row = grid.length;
      int col = grid[0].length;

      for (int i = 0; i < row; i++) {
          for (int j = 0; j < col; j++) {
              if(grid[i][j] >0){
                  maxGold = Math.max(maxGold, dfsGold(grid,i,j));
              }
          }
      }
      return maxGold;
    }

    private static int dfsGold(int[][] grid, int row , int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }

        int gold = grid[row][col];
        grid[row][col] = 0;
        int up = dfsGold(grid, row - 1, col);
        int down = dfsGold(grid, row + 1, col);
        int left = dfsGold(grid, row, col - 1);
        int right = dfsGold(grid, row, col + 1);
        grid[row][col] = gold;

        return Math.max( Math.max(up, down) ,Math.max(left, right)) + gold;
    }
}
