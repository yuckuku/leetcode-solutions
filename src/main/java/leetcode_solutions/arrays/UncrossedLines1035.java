package leetcode_solutions.arrays;

/**
 * @author: L'Nan
 * Date: 2019/5/10 10:54
 * Description:
 */
public class UncrossedLines1035 {
    class Solution {
        public int maxUncrossedLines(int[] A, int[] B) {
            int[][] dp = new int[A.length + 1][B.length + 1];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (A[i] == B[j])
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    else
                        dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);

                }
            }
            return dp[A.length][B.length];
        }
    }

    /**
     * dp
     */
    class SolutionOnLeetCode1 {
        public int maxUncrossedLines(int[] A, int[] B) {
            int[][] c = new int[A.length + 1][B.length + 1];

            for (int i = 1; i <= A.length; i++) {
                for (int j = 1; j <= B.length; j++) {
                    if (A[i - 1] == B[j - 1]) {
                        c[i][j] = c[i - 1][j - 1] + 1;
                    } else {
                        c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                    }
                }
            }
            return c[A.length][B.length];
        }
    }
}
