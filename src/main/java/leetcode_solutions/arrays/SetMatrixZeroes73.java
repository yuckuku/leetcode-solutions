package leetcode_solutions.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: L'Nan
 * Date: 2019/4/25 20:45
 * Description:
 */
public class SetMatrixZeroes73 {
    class Solution {
        public void setZeroes(int[][] matrix) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> lineSet = new HashSet<>();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0) {
                        rowSet.add(i);
                        lineSet.add(j);
                    }
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (rowSet.contains(i) || lineSet.contains(j)) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }

    /**
     * in-place
     */
    class SolutionOnLeetCode1 {
        public void setZeroes(int[][] matrix) {
            //如果首行或首列有元素为0，在最后将行或列置为0
            boolean rowFlag = false;
            //判断首行
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[0][i] == 0) {
                    rowFlag = true;
                    break;
                }
            }

            //判断首列
            boolean colFlag = false;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == 0) {
                    matrix[0][0] = 0;
                    break;
                }
            }

            //把对应的首行首列置为0，从[1][1]开始遍历
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            //行置0
            for (int i = 1; i < matrix[0].length; i++) {
                if (matrix[0][i] == 0) {
                    for (int j = 0; j < matrix.length; j++) {
                        matrix[j][i] = 0;
                    }
                }
            }
            //列置0
            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][0] == 0) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        matrix[i][j] = 0;
                    }
                }
            }
            if (matrix[0][0] == 0) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
            }
            //如果首行或首列中有0，将首行首列置为0
            if (rowFlag) {
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[0][i] = 0;
                }
            }
        }
    }

}
