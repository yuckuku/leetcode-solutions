package leetcode_solutions;

/**
 * Created by Administrator on 2017/10/12.
 */
public class MaxAreaOfIsland {

    int[][] grid;
    boolean[][] seen;

    public int area(int line, int row) {

        if (line < 0 || line >= grid.length || row < 0 || row >= grid[0].length || seen[line][row] || grid[line][row] == 0) {
            return 0;
        }
        seen[line][row] = true;
        return (1 + area(line - 1, row) + area(line + 1, row) + area(line, row - 1) + area(line, row + 1));
    }

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                maxArea = Math.max(maxArea, area(i, j));
            }
        }
        return maxArea;
    }


}