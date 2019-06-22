package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/27 20:51
 * Description:
 */
public class UniquePathsII63 {
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] a = new int[m][n];
            for (int i = 0; i < m; i++) {
                if (obstacleGrid[i][0] != 1) a[i][0] = 1;
                else break;
            }
            for (int i = 0; i < n; i++) {
                if (obstacleGrid[0][i] != 1) a[0][i] = 1;
                else break;
            }
            int i = 1, j = 1;
            while (i < m && j < n) {
                int k = j;
                for (; k < n; k++) {
                    if (obstacleGrid[i][k] == 1) a[i][k] = 0;
                    else a[i][k] = a[i - 1][k] + a[i][k - 1];
                }
                int l = i;
                for (; l < m; l++) {
                    if (obstacleGrid[l][j] == 1) a[l][j] = 0;
                    else a[l][j] = a[l - 1][j] + a[l][j - 1];
                }
                i++;
                j++;
            }
            return a[m - 1][n - 1];
        }
    }

    class SolutionOnLeetcode1 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] dp = new int[m][n];
            boolean barrier = false;
            for (int i = 0; i < n; i++) {
                if (obstacleGrid[0][i] == 1) {
                    barrier = true;
                }
                if (barrier) break;
                dp[0][i] = 1;
            }
            barrier = false;
            for (int i = 0; i < m; i++) {
                if (obstacleGrid[i][0] == 1) {
                    barrier = true;
                }
                if (barrier) break;
                dp[i][0] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
