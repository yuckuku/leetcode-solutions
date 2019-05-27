package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/5/17 15:44
 * Description:
 */
public class SpiralMatrix54 {
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> re = new ArrayList<>();
            if (null == matrix || matrix.length == 0)
                return re;
            int m = matrix.length, n = matrix[0].length;
            int[][] t = new int[m][n];
            int i = 0, j = 0;
            int d = 0;
            int count = 1;
            re.add(matrix[i][j]);
            t[i][j] = 1;
            while (count < m * n) {
                switch (d % 4) {
                    case 0:
                        if (j + 1 < n && t[i][j + 1] == 0) {
                            j++;
                        } else {
                            d++;
                            i++;
                        }
                        break;
                    case 1:
                        if (i + 1 < m && t[i + 1][j] == 0) {
                            i++;
                        } else {
                            d++;
                            j--;
                        }
                        break;
                    case 2:
                        if (j - 1 >= 0 && t[i][j - 1] == 0) {
                            j--;
                        } else {
                            d++;
                            i--;
                        }
                        break;
                    case 3:
                        if (i - 1 >= 0 && t[i - 1][j] == 0) {
                            i--;
                        } else {
                            d++;
                            j++;
                        }
                        break;
                    default:
                        break;
                }
                re.add(matrix[i][j]);
                count++;
                t[i][j] = 1;
            }
            return re;
        }
    }

    class SolutionOnLeetcode1 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> result = new ArrayList();
            int m = matrix.length;
            if (m < 1) {
                return result;
            }
            int n = matrix[0].length;
            int row = 0, col = 0, circle = 0;
            for (int i = 0; i < m * n; i++) {
                result.add(matrix[row][col]);
                if ((row == circle) && (col < n - 1 - circle)) {
                    col++;
                } else if ((row < m - 1 - circle) && (col == n - 1 - circle)) {
                    row++;
                } else if ((row == m - 1 - circle) && (col > circle)) {
                    col--;
                } else if ((col == circle) && (row > circle)) {
                    if (row == circle + 1) {
                        col++;
                        circle++;
                    } else {
                        row--;
                    }

                }

            }
            return result;
        }
    }

    @Test
    public void test() {
        int[][] m = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}

        };

        Solution s = new Solution();

        List<Integer> re = s.spiralOrder(m);
        System.out.println(re);
    }
}
