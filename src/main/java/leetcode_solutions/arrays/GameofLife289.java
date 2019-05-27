package leetcode_solutions.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: L'Nan
 * @Date: 2019/4/3 15:57
 * @Description:
 */
public class GameofLife289 {
    /**
     *
     */
    class MySolution {
        public void gameOfLife(int[][] board) {
            int[][] t = nextState(board);
            copy2D(board, t);
        }

        private int[][] nextState(int[][] b) {
            int[][] t = new int[b.length][b[0].length];
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b[i].length; j++) {
                    int state = nextState(b, i, j);
                    t[i][j] = state;
                }
            }
            return t;
        }

        private int nextState(int[][] b, int i, int j) {
            int state = 0;
            int liveNeighbors = 0;
            if (i - 1 >= 0) {
                if (j - 1 >= 0 && b[i - 1][j - 1] == 1) {
                    liveNeighbors++;
                }
                if (b[i - 1][j] == 1) {
                    liveNeighbors++;
                }
                if (j + 1 < b[i].length && b[i - 1][j + 1] == 1) {
                    liveNeighbors++;
                }
            }
            if (j - 1 >= 0 && b[i][j - 1] == 1) {
                liveNeighbors++;
            }
            if (j + 1 < b[i].length && b[i][j + 1] == 1) {
                liveNeighbors++;
            }
            if (i + 1 < b.length) {
                if (j - 1 >= 0 && b[i + 1][j - 1] == 1) {
                    liveNeighbors++;
                }
                if (b[i + 1][j] == 1) {
                    liveNeighbors++;
                }
                if (j + 1 < b[i].length && b[i + 1][j + 1] == 1) {
                    liveNeighbors++;
                }
            }

            switch (liveNeighbors) {
                case 2:
                    state = b[i][j];
                    break;
                case 3:
                    state = 1;
                    break;
                default:
                    state = 0;
                    break;
            }
            return state;
        }


        private void copy2D(int[][] b, int[][] t) {
            for (int i = 0; i < t.length; i++) {
                for (int j = 0; j < t[0].length; j++) {
                    b[i][j] = t[i][j];
                }
            }
        }
    }

    /**
     * 2ms
     */
    class SolutionOnLeetCode {
        public void gameOfLife(int[][] board) {
            int m = board.length;
            int n = board[0].length;
            int[][] result = new int[m][n];
            loop:
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (m == 1 && n == 1) {
                        result[i][j] = 0;
                        break loop;
                    }
                    int num = 0;
                    if (m == 1) {
                        if (j == 0) num = board[i][j + 1];
                        else if (j == n - 1) num = board[i][j - 1];
                        else num = board[i][j - 1] + board[i][j + 1];
                    } else if (n == 1) {
                        if (i == 0) num = board[i + 1][j];
                        else if (i == m - 1) num = board[i - 1][j];
                        else num = board[i - 1][j] + board[i + 1][j];
                    } else if (i == 0) {
                        if (j == 0) {
                            num = board[i + 1][j] + board[i][j + 1] + board[i + 1][j + 1];
                        } else if (j == n - 1) {
                            num = board[i + 1][j] + board[i][j - 1] + board[i + 1][j - 1];
                        } else
                            num = board[i][j - 1] + board[i][j + 1] + board[i + 1][j - 1] + board[i + 1][j] + board[i + 1][j + 1];
                    } else if (i == m - 1) {
                        if (j == 0) {
                            num = board[i - 1][j] + board[i][j + 1] + board[i - 1][j + 1];
                        } else if (j == n - 1) {
                            num = board[i - 1][j] + board[i][j - 1] + board[i - 1][j - 1];
                        } else
                            num = board[i][j - 1] + board[i][j + 1] + board[i - 1][j - 1] + board[i - 1][j] + board[i - 1][j + 1];
                    } else if (j == 0) {
                        num = board[i - 1][j] + board[i + 1][j] + board[i - 1][j + 1] + board[i][j + 1] + board[i + 1][j + 1];
                    } else if (j == n - 1) {
                        num = board[i - 1][j] + board[i + 1][j] + board[i - 1][j - 1] + board[i][j - 1] + board[i + 1][j - 1];
                    } else {
                        num = board[i - 1][j - 1] + board[i - 1][j] + board[i - 1][j + 1] + board[i][j - 1] + board[i][j + 1] + board[i + 1][j - 1] + board[i + 1][j] + board[i + 1][j + 1];
                    }

                    if (board[i][j] == 0 && num == 3) result[i][j] = 1;
                    else if (board[i][j] == 1) {
                        if (num == 2 || num == 3) result[i][j] = 1;
                        else result[i][j] = 0;
                    } else result[i][j] = 0;
                }
            }
            System.arraycopy(result, 0, board, 0, m);
        }
    }

}
