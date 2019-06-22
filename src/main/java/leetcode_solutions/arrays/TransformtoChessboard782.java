package leetcode_solutions.arrays;

import org.junit.Test;

/**
 * @author: L'Nan
 * Date: 2019/6/12 15:37
 * Description:
 */
public class TransformtoChessboard782 {
    class Solution {
        public int movesToChessboard(int[][] board) {
            int N = board.length, rowSum = 0, colSum = 0, rowSwap = 0, colSwap = 0;

            //检查四个角
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if ((board[0][0] ^ board[i][0] ^ board[0][j] ^ board[i][j]) == 1) {
                        return -1;
                    }
                }
            }


            for (int i = 0; i < N; ++i) {
                rowSum += board[0][i];//第一行和
                colSum += board[i][0];//第一列和
                rowSwap += board[i][0] == i % 2 ? 1 : 0;
                colSwap += board[0][i] == i % 2 ? 1 : 0;
            }
            if (N / 2 > rowSum || rowSum > (N + 1) / 2) {
                return -1;
            }
            if (N / 2 > colSum || colSum > (N + 1) / 2) {
                return -1;
            }
            if (N % 2 == 1) {
                if (colSwap % 2 == 1) {
                    colSwap = N - colSwap;
                }
                if (rowSwap % 2 == 1) {
                    rowSwap = N - rowSwap;
                }
            } else {
                colSwap = Math.min(N - colSwap, colSwap);
                rowSwap = Math.min(N - rowSwap, rowSwap);
            }

            return (colSwap + rowSwap) / 2;
        }
    }

    @Test
    public void test() {
//        int[][] board = new int[][]{
//                {1, 1, 0}, {0, 0, 1}, {0, 0, 1}
//        };
//        Solution s = new Solution();
//        int re = s.movesToChessboard(board);
//        System.out.println(re);
        System.out.println(1 ^ 1);
        System.out.println(0 ^ 1);
        System.out.println(0 ^ 0);
        System.out.println(1 ^ 0);

    }
}
