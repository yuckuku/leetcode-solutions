package leetcode_solutions;

public class BattleshipsinaBoard419 {
    class Solution {
        public int countBattleships(char[][] board) {
            return count(board);
        }

        private int count(char[][] b) {
            int m = b.length;
            int n = b[0].length;
            int re = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (b[i][j] == 'X') {
                        //横着
                        if (j + 1 < n && b[i][j + 1] == 'X') {
                            while (j < n && b[i][j] == 'X') {
                                if (horizontalValid(b, m, n, i, j)) {
                                    j++;
                                } else {
                                    return 0;
                                }
                            }
                            re++;
                        } else if (i + 1 < m && b[i + 1][j] == 'X') {
                            continue;
                        } else {
                            re++;
                        }
                    }
                }
            }
            return re;
        }

        private boolean horizontalValid(char[][] b, int m, int n, int i, int j) {
            if (i - 1 >= 0 && b[i - 1][j] == 'X' || i + 1 < m && b[i + 1][j] == 'X') return false;
            return true;
        }

        private boolean verticalValid(char[][] b, int m, int n, int i, int j) {
            if (j - 1 >= 0 && b[i][j - 1] == 'X' || j + 1 < n && b[i][j + 1] == 'X') return false;
            return true;
        }
    }

    class Solution1 {
        public int countBattleships(char[][] board) {
            int count = 0, i, j;
            for (i = 0; i < board.length; ++i) {
                for (j = 0; j < board[i].length; ++j) {
                    if ((board[i][j] == 'X') && (i == 0 || board[i - 1][j] == '.') && (j == 0 || board[i][j - 1] == '.'))
                        ++count;
                }
            }
            return count;
        }
    }

    class Solution_dfs {
        public int countBattleships(char[][] board) {
            if (board == null || board.length < 1 || board[0] == null || board[0].length < 1) {
                return 0;
            }

            int ans = 0;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 'X') {
                        dfs(board, i, j);
                        ans++;
                    }
                }
            }

            return ans;
        }

        private void dfs(char[][] board, int i, int j) {
            board[i][j] = '.';

            if (i + 1 < board.length && board[i + 1][j] == 'X') {
                dfs(board, i + 1, j);
            }

            if (j + 1 < board[i].length && board[i][j + 1] == 'X') {
                dfs(board, i, j + 1);
            }
        }
    }


}
