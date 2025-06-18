package src.companies.altimatrick;

import java.util.LinkedList;
import java.util.Queue;

public class FindTimeForRottanOrrange {
    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Minimum time to rot all oranges: " + orangesRotting(grid)); // Output: 4
    }
    static class Cell {
        int row, col, time;
        Cell(int r, int c, int t) {
            this.row = r;
            this.col = c;
            this.time = t;
        }
    }

    public static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Cell> queue = new LinkedList<>();
        int freshCount = 0;

        // Initialize queue with all rotten oranges and count fresh
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2)
                    queue.offer(new Cell(i, j, 0));
                else if (grid[i][j] == 1)
                    freshCount++;
            }
        }

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int time = 0;

        // BFS traversal
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            time = current.time;

            for (int[] dir : directions) {
                int r = current.row + dir[0];
                int c = current.col + dir[1];

                // Infect fresh oranges
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
                    grid[r][c] = 2; // rot it
                    freshCount--;
                    queue.offer(new Cell(r, c, time + 1));
                }
            }
        }

        return freshCount == 0 ? time : -1;
    }
}
