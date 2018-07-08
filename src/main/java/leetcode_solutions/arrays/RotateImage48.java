package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

public class RotateImage48 {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int temp;
        for (int k = 0; k < len / 2; k++) {//round offset
            for (int i = 0; i < matrix.length - k * 2 - 1; i++) {//with in round offset
                System.out.println("before one rotate");
                System.out.println(matrix[k][k + i] + " " + matrix[k + i][len - k - 1] + " " + matrix[len - k - 1 ][len - k - 1- i] + " " + matrix[len-1-k-i][k]);
                rotate(matrix[k][k + i], matrix[k + i][len - k - 1], matrix[len - k - 1 ][len - k - 1- i], matrix[len-1-k-i][k]);
                temp = matrix[k][k + i];
                matrix[k][k + i] = matrix[len-1-k-i][k];
                matrix[len-1-k-i][k] = matrix[len - k - 1 ][len - k - 1- i];
                matrix[len - k - 1 ][len - k - 1- i] = matrix[k + i][len - k - 1];
                matrix[k + i][len - k - 1] = temp;
                System.out.println("after one rotate");
            }
        }
    }

    private void rotate(int a, int b, int c, int d) {
        int temp = a;
        a = d;
        d = c;
        c = b;
        b = temp;
    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
//        rotate(matrix[0][0],matrix[0][2],matrix[2][2],matrix[2][0]);
//        int temp = matrix[0][0];
//        matrix[0][0] = matrix[2][0];
//        matrix[2][0] = matrix[2][2];
//        matrix[2][2] = matrix[0][2];
//        matrix[0][2] = temp;
        for (int[] ma :
                matrix) {
            System.out.println(Arrays.toString(ma));
        }
    }

}
