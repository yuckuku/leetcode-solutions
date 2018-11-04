package leetcode_solutions.arrays;

import org.junit.Test;

import javax.xml.stream.XMLOutputFactory;
import java.util.concurrent.ForkJoinPool;

public class MaximalRectangle85 {
    //my solution:find every possible max rectangle with matrix[i],[j] as left vertex, loop matrix.
    //proved to be right answer
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, maxStartPoint(matrix, i, j));
            }
        }
        return max;
    }

    private int maxStartPoint(char[][] matrix, int i, int j) {
        int max = 0;
        int temp = 0;
        for (int k = i; k < matrix.length; k++) {
            if (matrix[k][j] == '0') {
                break;
            }

            int block = k - i + 1;
            temp += block;
            for (int l = j + 1; l < matrix[i].length; l++) {
                boolean flag = true;
                for (int m = i; m <= k; m++) {
                    if (matrix[m][l] == '0') {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
                temp += block;
            }

            max = Math.max(max, temp);
            temp = 0;
        }
        return max;
    }


    private boolean checkRectangle(char[][] matrix) {
        int k = 0;
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                k++;
                sum += (matrix[i][j] - '0');
                if (k != sum) {
                    return false;
                }
            }
        }
        return true;
    }

    public int maximalRectangle0(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int max = 0;
        int m = matrix.length;
        int n = matrix[m - 1].length;

        int[][] metrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    if (matrix[i][j] == '1') {
                        metrix[i][j] = 1;
                    } else
                        metrix[i][j] = 0;
                } else {
                    if (matrix[i][j] == '0')
                        metrix[i][j] = 0;
                    else {
                        metrix[i][j] = metrix[i][j - 1] + 1;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (metrix[i][j] == 0)
                    continue;
                int count = 1;
                for (int k = i - 1; k >= 0 && metrix[k][j] >= metrix[i][j]; k--, count++) ;
                for (int k = i + 1; k < m && metrix[k][j] >= metrix[i][j]; k++, count++) ;

                if (max < metrix[i][j] * count) {
                    max = metrix[i][j] * count;
//                    System.out.println("i:"+i);
//                    System.out.println("j:"+j);
//                    System.out.println("max:"+max);

                }
            }
        }
        return max;
    }

    @Test
    public void test() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        maximalRectangle0(matrix);

        /*int[][] metrix=forTest(matrix);
        for (int[] temp:
        metrix){
            for (int i :
                    temp) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }*/
    }

    /*
    private int[][] forTest(char[][] matrix){
        int m = matrix.length;
        int n = matrix[m - 1].length;

        int[][] metrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    if (matrix[i][j] == '1') {
                        metrix[i][j] = 1;
                    } else
                        metrix[i][j] = 0;
                } else {
                    if (matrix[i][j] == '0')
                        metrix[i][j] = 0;
                    else {
                        metrix[i][j] = metrix[i][j - 1] + 1;
                    }
                }
            }
        }
        return metrix;
    }*/

}
