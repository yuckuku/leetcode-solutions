package leetcode_solutions;

import org.junit.Test;

public class SpiralMatrixII59 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int topOffset = 0;
        int rightOffset = 0;
        int bottomOffset = 0;
        int leftOffset = 0;
        int index = 1;

        int i = 0;

        while (topOffset + bottomOffset < n) {
            // top
            for (i = leftOffset; i < n - rightOffset; i++) {
                result[topOffset][i] = index++;
            }
            topOffset++;
            // right
            for (i = topOffset; i < n - bottomOffset; i++) {
                result[i][n - 1 - rightOffset] = index++;
            }
            rightOffset++;
            // bottom
            for (i = n - 1 - rightOffset; i >= leftOffset; i--) {
                result[n - 1 - bottomOffset][i] = index++;
            }
            bottomOffset++;
            // left
            for (i = n - 1 - bottomOffset; i >= topOffset; i--) {
                result[i][leftOffset] = index++;
            }
            leftOffset++;
        }

        return result;
    }

    public int[][] generateMatrix0(int n) {
        int len = n;
        int[][] dp = new int[len][len];
        int f = 1;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (len - 1 - 2 * i); j++) {
                dp[i][i + j] = f++;
            }
            for (int j = 0; j < (len - 1 - 2 * i); j++) {
                dp[i + j][len - 1 - i] = f++;
            }
            for (int j = 0; j < (len - 1 - 2 * i); j++) {
                dp[len - 1 - i][len - 1 - i - j] = f++;
            }
            for (int j = 0; j < (len - 1 - 2 * i); j++) {
                dp[len - 1 - i - j][i] = f++;
            }
        }
        if (n % 2 == 1) {
            dp[n / 2][n / 2] = f;
        }
        return dp;
    }

    @Test
    public void test(){
        int[][] re=generateMatrix0(5);


    }

}
