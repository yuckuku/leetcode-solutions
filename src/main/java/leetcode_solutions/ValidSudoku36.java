package leetcode_solutions;

import org.junit.Test;

public class ValidSudoku36 {
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            //第一维度为数字-1，第二维度为rows
            boolean[][] r = new boolean[9][9];

            //第一维度为数字，第二维度为columns
            boolean[][] c = new boolean[9][9];

            //第一维度为数字，第二维度为sub-boxes
            boolean[][] b = new boolean[9][9];

            //开始检查
            //box 0
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][0]) {
                            System.out.println("box0");
                            System.out.println();
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][0] = true;
                    }
                }
            }

            //box 1
            for (int i = 0; i <= 2; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][1]) {
                            System.out.println("box1");
                            System.out.println();
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][1] = true;
                    }
                }
            }

            //box 2
            for (int i = 0; i <= 2; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][2]) {
                            System.out.println("box2");
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][2] = true;
                    }
                }
            }

            //box 3
            for (int i = 3; i <= 5; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][3]) {
                            System.out.println("box3");
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][3] = true;
                    }
                }
            }

            //box 4
            for (int i = 3; i <= 5; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][4]) {
                            System.out.println("box4");
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][4] = true;
                    }
                }
            }

            //box 5
            for (int i = 3; i <= 5; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][5]) {
                            System.out.println("box5");
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][5] = true;
                    }
                }
            }

            //box 6
            for (int i = 6; i <= 8; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][6]) {
                            System.out.println("box6");
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][6] = true;
                    }
                }
            }

            //box 7
            for (int i = 6; i <= 8; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][7]) {
                            System.out.println("box7");
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][7] = true;
                    }
                }
            }

            //box 8
            for (int i = 6; i <= 8; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (board[i][j] == '.') continue;
                    else {
                        int num = board[i][j] - '0';
                        if (r[num - 1][i] || c[num - 1][j] || b[num - 1][8]) {
                            System.out.println("box8");
                            System.out.println(num);
                            return false;
                        }
                        r[num - 1][i] = true;
                        c[num - 1][j] = true;
                        b[num - 1][8] = true;
                    }
                }
            }

            return true;
        }
    }

    /**
     * 主要两个要点：
     * 1、只遍历一次如何储存数据；
     * 2、判断是在一个3*3的框中的方法。
     * <p>
     * 1、使用了2进制的9个位数，如果是第一个数是1，那么统计标志就是0000000010(二进制 1左移1位)，
     * 如果第二个数是3那么统计标识变为0000001010(二进制 1左移3位再加上原来的)，
     * 每次判断有没有重复就右移相应位数之后整除2即可。
     * 2、同官方解法int boxNum = i / 3 * 3 + j / 3;如果是0,1,2行的话整除3就是0，
     * 然后再加上列数整除3，这样就把整个9*9分为了编号0-8的9个3*3的区域。
     */
    class Solution1 {

        public boolean isValidSudoku(char[][] board) {
            int[] rowCnt = new int[9];
            int[] colCnt = new int[9];
            int[] boxCnt = new int[9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if ('.' == board[i][j]) {
                        continue;
                    }
                    int num = board[i][j] - 48;
                    // 处理行
                    if ((rowCnt[i] >> num) % 2 == 1) {
                        return false;
                    } else {
                        rowCnt[i] += 1 << num;
                    }
                    // 处理列
                    if ((colCnt[j] >> num) % 2 == 1) {
                        return false;
                    } else {
                        colCnt[j] += 1 << num;
                    }
                    // 处理框
                    int boxNum = i / 3 * 3 + j / 3;
                    if ((boxCnt[boxNum] >> num) % 2 == 1) {
                        return false;
                    } else {
                        boxCnt[boxNum] += 1 << num;
                    }
                }
            }
            return true;
        }
    }

    @Test
    public void test() {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Solution solution = new Solution();
        boolean re = solution.isValidSudoku(board);
        System.out.println(re);

        System.out.println("-----------");
        System.out.println((int) '0');
        int tt = 0;
        tt += 1 << 1;
        System.out.println(tt);
    }
}
