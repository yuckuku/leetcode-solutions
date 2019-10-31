package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

public class ScoreAfterFlippingMatrix861 {
    class Solution {
        public int matrixScore(int[][] A) {
            //权重数组，用以辅助计算
            int[] values = new int[A[0].length];
            values[values.length - 1] = 1;
            for (int i = values.length - 2; i >= 0; i--) {
                values[i] = values[i + 1] << 1;
            }

            //把第一列tog成全1
            for (int i = 0; i < A.length; i++) {
                if (A[i][0] != 1) {
                    //tog第i行
                    for (int j = 0; j < A[0].length; j++) {
                        A[i][j] = tog(A[i][j]);
                    }
                }
            }

            int re = 0;
            for (int j = 0; j < A[0].length; j++) {
                int count = 0;
                for (int i = 0; i < A.length; i++) {
                    if (A[i][j] == 1) count++;
                }
                re += values[j] * (count > A.length - count ? count : A.length - count);
            }
            return re;
        }

        private int tog(int a) {
            if (a == 0) return 1;
            else return 0;
        }
    }

    @Test
    public void test() {
        int[][] A = new int[][]{{0, 1, 1}, {1, 1, 1}, {0, 1, 0}};
        Solution solution = new Solution();
        int re = solution.matrixScore(A);
        for (int i = 0; i < A.length; i++) {
            System.out.println(Arrays.toString(A[i]));
        }
        System.out.println(re);
    }
}
