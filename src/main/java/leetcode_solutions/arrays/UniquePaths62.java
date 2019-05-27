package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.zip.CheckedOutputStream;

/**
 * @author: L'Nan
 * Date: 2019/4/20 12:49
 * Description:
 */
public class UniquePaths62 {

    /**
     * tle,超时
     */
    class Solution {
        int count = 0;

        public int uniquePaths(int m, int n) {
            path(1, 1, m, n);
            return count;
        }

        private void path(int i, int j, int m, int n) {
            if (i < m && j < n) {
                path(i + 1, j, m, n);
                path(i, j + 1, m, n);
            } else {
                count++;

            }
        }
    }

    /**
     * 路径
     */
    class Solution1 {

        public int uniquePaths(int m, int n) {
            int[][] path = new int[m][n];


            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    int up = path[i - 1][j] == 0 ? 1 : path[i - 1][j];
                    int down = path[i][j - 1] == 0 ? 1 : path[i][j - 1];
                    path[i][j] = up + down;
                }
            }
            return path[m - 1][n - 1] == 0 ? 1 : path[m - 1][n - 1];
        }
    }

    /**
     * 组合数学
     * 答案即C(m+n-2,m-1)或C(m+n-2,n-1)
     */
    class SolutionOnLeetCode1 {
        public int uniquePaths(int m, int n) {
            if (m == 1 || n == 1)
                return 1;
            m--;
            n--;
            if (m < n) {
                m = m + n;
                n = m - n;
                m = m - n;
            }
            long res = 1;
            int j = 1;
            for (int i = m + 1; i <= m + n; i++, j++) {
                res *= i;
                res /= j;
            }

            return (int) res;
        }
    }

    @Test
    public void test() {
        long start = System.nanoTime();
        int re = new SolutionOnLeetCode1().uniquePaths(51, 9);
        long end = System.nanoTime();

        System.out.println(end - start);
        System.out.println(re);
    }

}
